package controller.donation;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.DonationComment;
import model.DonationImage;
import model.DonationReceipt;
import model.Donator;
import model.ReceiptImage;
import model.SocialGroupArticle;
import model.service.CommentManager;
import model.service.DonationManager;
import model.service.DonationReceiptManager;

import model.service.SocialGroupManager;
import model.service.UserNotFoundException;

public class ViewSocialGroupArticleController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

    	final Logger log = LoggerFactory.getLogger(ViewSocialGroupArticleController.class);

        
        try {
            SocialGroupManager socialGroupMan = SocialGroupManager.getInstance();
            LocalDate now  = LocalDate.now();
			request.setAttribute("cTime", now);
            
            int articleId = Integer.parseInt(request.getParameter("articleId"));

            SocialGroupArticle article = socialGroupMan.findSocialGroupArticleByArticleId(articleId);
            List<DonationImage> imageList = socialGroupMan.findImageList(articleId);
			article.setImageList(imageList);
            System.out.println("articleInfo: "+article);
            request.setAttribute("article", article);
            
            CommentManager comm_man = CommentManager.getInstance();
            List<DonationComment> comment_l = comm_man.findAllComment(articleId);
            request.setAttribute("comment", comment_l);

            
            DonationManager donationMan = DonationManager.getInstance();
			List<Donator> donatorList = donationMan.latest10Donors(articleId);
			
			request.setAttribute("donatorList", donatorList);
			
			if (article.getReceiptCheck().equals("Y")) {
				DonationReceiptManager receipt_man = DonationReceiptManager.getInstance();
				DonationReceipt receipt = receipt_man.findByArticleId(articleId);
				
				request.setAttribute("donationReceipt", receipt);
				List<ReceiptImage> receiptImageList = receipt_man.findImageByReceiptId(receipt.getReceiptId());
				request.setAttribute("receiptImageList", receiptImageList);
				log.debug("receipt: {}", receipt);
				log.debug("receiptImageList: {}", receiptImageList);
			}

            return "/donationList/socialGroupArticle.jsp";
        } catch (ArithmeticException e) {
            // TODO: handle exception
            throw new UserNotFoundException("존재하지 않는 후원글입니다.");
        }
    }


}

