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
import model.DonationImage;
import model.SocialGroupArticle;
import model.service.SocialGroupManager;

public class SocialGroupArticleController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(SocialGroupArticleController.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            // GET request: article 생성 form 요청
            log.debug("SocialGroupForm Request");
            return "/donationForm/socialGroupForm.jsp";
        }
        // POST request (정보가 parameter로 전송됨)
        //user
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        //article - (부모클래스: DonationArticle  자식클래스: SocialGroupArticle)
        SocialGroupArticle article = new SocialGroupArticle();
        //이미지 리스트
        List<DonationImage> imageList = new ArrayList<DonationImage>();
        //이미지 정보
        String filename = null;
        int fileOrder = 1;
    
        boolean check = ServletFileUpload.isMultipartContent(request);              
        if(check) {    // 전송된 요청 메시지의 타입이 multipart 인지 여부를 체크한다. (multipart/form-data)
            // 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트 밑에 upload 폴더가 생성됨 
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/upload");
            File dir = new File(path);          
            
            // Tomcat 외부의 폴더에 저장하려면 아래와 같이 절대 경로로 폴더 이름을 지정함
            // File dir = new File("C:/Temp");
                        
            if (!dir.exists()) dir.mkdir();  // 전송된 파일을 저장할 폴더 생성
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                    // 파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                    // 메모리에 한번에 저장할 데이터의 크기를 10KB로 설정한다.
                factory.setRepository(dir);
                    // 전송된 데이터의 내용을 저장할 폴더를 지정한다.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                    // Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                    // 업로드 될 파일의 최대 용량을 10MB로 설정한다.
                upload.setHeaderEncoding("utf-8");
                    // 업로드 되는 내용의 인코딩 방식을 설정한다.
                                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);                
                    // upload 객체에 전송된 모든 데이터(요청 파라미터)를 Collection 객체에 담는다.
                
                for (int i = 0; i < items.size(); ++i) {
                    FileItem item = (FileItem)items.get(i);            
                    String value = item.getString("utf-8"); // 넘어온 값에 대한 한글 처리를 한다.                 
                    if (item.isFormField()) {  // item이 일반 데이터인 경우                      
                        //DonationArticle의 필드
                        if(item.getFieldName().equals("title"))
                          article.setTitle(value);
                            // parameter 이름이 title이면 title 변수에 값을 저장한다.
                        else if(item.getFieldName().equals("deadline"))
                            article.setDeadline(value);
                            // parameter 이름이 deadline이면 deadline 변수에 값을 저장한다.
                        else if(item.getFieldName().equals("bank_name"))
                            article.setBankName(value);
                            // parameter 이름이 bank_name이면 bankName 변수에 값을 저장한다.
                        else if(item.getFieldName().equals("acc_holder"))
                            article.setAccHolder(value);
                        else if(item.getFieldName().equals("acc_num"))
                            article.setAccNum(value);
                        else if(item.getFieldName().equals("due_date"))
                            article.setDueDate(value);
                        else if(item.getFieldName().equals("use_plan"))
                            article.setUsePlan(value);
                        else if(item.getFieldName().equals("other_text"))
                            article.setOtherText(value);
                        else if(item.getFieldName().equals("due_date"))
                            article.setDueDate(value);
                        //socialGroupArticle의 필드
                        else if(item.getFieldName().equals("age"))
                            article.setAge(Integer.parseInt(value));
                        else if(item.getFieldName().equals("gender"))
                            article.setGender(value);
                        else if(item.getFieldName().equals("type"))
                            article.setType(value);
                        else if(item.getFieldName().equals("area"))
                            article.setArea(value);
                        else if(item.getFieldName().equals("situation"))
                            article.setSituation(value);
                    }
                    else {  // item이 파일인 경우   
                        if (item.getFieldName().equals("image")) {
                            // parameter 이름이 image이면 파일 저장을 한다.
                            String oriFilename = item.getName();    // 파일 이름 획득 (자동 한글 처리 됨)
                            if (oriFilename == null || oriFilename.trim().length() == 0) continue;
                                // 파일이 전송되어 오지 않았다면 건너뜀
                            
                            // filename = oriFileName.substring(oriFileName.lastIndexOf("\\") + 1);                                          
                                // 파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                                // 실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드임
                            
                            filename = UUID.randomUUID().toString() + "_" 
                                    + oriFilename.substring(oriFilename.lastIndexOf("\\") + 1);  
                                // 파일 이름이 중복되지 않도록 UUID(Universally Unique IDentifier)를 생성해서 원래의 이름 앞에 붙임
                                
                            // 또는 아래와 같이 원래의 파일의 확장자만 추출해서 UUID와 함께 파일 이름 생성
                            // filename = UUID.randomUUID().toString() 
                            //      + oriFilename.substring(oriFilename.lastIndexOf("."));
                            
                            //DonationImage 생성 후 List<DonationImage>에 저장 
                            DonationImage image = new DonationImage();
                            image.setArticleId(0); //manager.create(article); 후 설정 가능
                            image.setFileOrder(fileOrder++);
                            image.setFileName(filename);
                            image.setArticle(article);
                            imageList.add(image);
                            
                            System.out.println("uploaded file: " + filename);
                            File file = new File(dir, filename);
                            item.write(file);
                                // 파일을 upload 경로에 실제로 저장한다.
                                // FileItem 객체로부터 바로 출력 저장할 수 있다.
                        }
                    }
                }
            } catch(SizeLimitExceededException e) {
                // 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
                e.printStackTrace();           
            } catch(FileUploadException e) {
                // 파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            } catch(Exception e) {            
                e.printStackTrace();
            }
            
            //request.setAttribute("filename", filename); //마지막 파일
            //System.out.println("filname: "+filename);
            
            //defualt값으로 지정할 필드
            article.setArticleId(0); //추후 변경
            article.setCategory("socialGroup"); //고정
            article.setIdCheck("Y"); //추후 변경
            article.setCreateDate(null); //추후 변경
            article.setUpdateDate(null); //추후 변경
            article.setReceiptCheck("N"); //추후 변경
            article.setTotalAmount(0); //추후 변경
            article.setUserId(userId); //session값, 고정
            
            //SoicalGroupArticle.imageList에 위에서 생성한 imageList 저장
            article.setImageList(imageList);
//            System.out.println("imgList 출력");
//            for (DonationImage list : article.getImageList()) {
//                System.out.println(list);
//            }
            
            log.debug("Create article : {}", article);
        }      
        
//        article.setImageList(imageList);
//        System.out.println("imgList 출력");
//        for (DonationImage list : article.getImageList()) {
//            System.out.println(list);
//        }
        
        try {
          SocialGroupManager manager = SocialGroupManager.getInstance();
          //SOICALGROUP_ARTICLE 테이블에 레코드 생성 후 article_id 컬럼에 저장된 값 가져오기
          int articleId = manager.create(article); 
          article.setArticleId(articleId);
          
          //DonationImage.articleId에 articleId값 저장
          for (int i=0; i<imageList.size(); i++)
              imageList.get(i).setArticleId(articleId);
          
          //DONATION_IMAGE 테이블에 레코드 생성
          for (int i=0; i<imageList.size(); i++)
              manager.createImage(imageList.get(i));
          return  "redirect:/donationList/socialGroup?articleId="+articleId;
        } catch (Exception e) { // 예외 발생 시 form으로 forwarding
            request.setAttribute("createFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("article", article);
            
            return "/donationForm/socialGroupForm.jsp";
        }
    }
}