package controller.donation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.DisasterArticle;
import model.DonationImage;
import model.service.DisasterManager;

public class DisasterArticleController implements Controller{
	 private static final Logger log = LoggerFactory.getLogger(DisasterArticleController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (request.getMethod().equals("GET")) {	
			return "/donationForm/disasterForm.jsp";  
	    }
    	
    	HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		
		DisasterArticle disaster = new DisasterArticle();
		List<DonationImage> imageList = new ArrayList<DonationImage>();
		
		String filename = null;
		int fileOrder = 1;
		
		boolean check = ServletFileUpload.isMultipartContent(request);
		if(check) {
			ServletContext context = request.getServletContext();
            String path = context.getRealPath("/upload");
            
            File dir = new File(path);      
            
            if (!dir.exists()) dir.mkdir(); 
            
            try {
            	 DiskFileItemFactory factory = new DiskFileItemFactory();
            	 factory.setSizeThreshold(10 * 1024);
            	 factory.setRepository(dir);
            	 
            	 ServletFileUpload upload = new ServletFileUpload(factory);
            	 upload.setSizeMax(10 * 1024 * 1024);
            	 upload.setHeaderEncoding("utf-8");
            	 
            	  List<FileItem> items = (List<FileItem>)upload.parseRequest(request);                
            	  
            	  for(int i=0; i<items.size(); i++) {
            		  FileItem item = (FileItem)items.get(i);        
            		  String value = item.getString("utf-8");
            		  if (item.isFormField()) {
            			  if(item.getFieldName().equals("title"))
            				  disaster.setTitle(value);
                           else if(item.getFieldName().equals("deadline"))
                        	   disaster.setDeadline(value);
                           else if(item.getFieldName().equals("bank_name"))
                        	   disaster.setBankName(value);
                           else if(item.getFieldName().equals("acc_holder"))
                        	   disaster.setAccHolder(value);
                           else if(item.getFieldName().equals("acc_num"))
                        	   disaster.setAccNum(value);
                           else if(item.getFieldName().equals("due_date"))
                        	   disaster.setDueDate(value);
                           else if(item.getFieldName().equals("use_plan"))
                        	   disaster.setUsePlan(value);
                           else if(item.getFieldName().equals("other_text"))
                        	   disaster.setOtherText(value);
                           else if(item.getFieldName().equals("area"))
                        	   disaster.setArea(value);
                           else if(item.getFieldName().equals("type"))
                        	   disaster.setType(value);
                           else if(item.getFieldName().equals("name"))
                        	   disaster.setName(value);
                           else if(item.getFieldName().equals("damage_amount"))
                        	   disaster.setDamageAmount(Integer.parseInt(value));
                           else if(item.getFieldName().equals("situation"))
                        	   disaster.setSituation(value);
            		  }
            		  else {
            			  if (item.getFieldName().equals("image")) {
            				  String oriFilename = item.getName();
            				  if (oriFilename == null || oriFilename.trim().length() == 0) continue;
            				  
            				  filename = UUID.randomUUID().toString() + "_" 
                                      + oriFilename.substring(oriFilename.lastIndexOf("\\") + 1);
            				 
            				  DonationImage image = new DonationImage();
                              image.setArticleId(0); //manager.create(article); 후 설정 가능
                              image.setFileOrder(fileOrder++);
                              image.setFileName(filename);
                              image.setArticle(disaster);
                              imageList.add(image);
            				  
                              System.out.println("uploaded file: " + filename);
                              File file = new File(dir, filename);
                              item.write(file);
            			  }
            		  }
            	  }
            }catch(SizeLimitExceededException e) {
                // 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
                e.printStackTrace();           
            } catch(FileUploadException e) {
                // 파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            } catch(Exception e) {            
                e.printStackTrace();
            }
            
            disaster.setArticleId(0); 
            disaster.setCategory("disaster");
            disaster.setIdCheck("Y"); 
            disaster.setCreateDate(null); 
            disaster.setUpdateDate(null); 
            disaster.setReceiptCheck("N"); 
            disaster.setTotalAmount(0); 
            disaster.setUserId(userId); 
            disaster.setImageList(imageList);
            
            log.debug("DisasterArticle Controller: {}", disaster);
		}
		
		try {
			DisasterManager manager = DisasterManager.getInstance();
			int articleId = manager.create(disaster);
			disaster.setArticleId(articleId);
			
			for (int i=0; i<imageList.size(); i++)
                imageList.get(i).setArticleId(articleId);
			
			//DONATION_IMAGE 테이블에 레코드 생성
            for (int i=0; i<imageList.size(); i++)
                manager.createImage(imageList.get(i));
            return "redirect:/donationList/disaster?articleId="+articleId; 
		}catch (Exception e) {
   		 request.setAttribute("createFailed", true);
   		 request.setAttribute("exception", e);
   		 request.setAttribute("animal", disaster);
   		 return "/donationForm/disasterForm.jsp";
		}
	}

}
