package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.AnimalArticle;
import model.DisasterArticle;
import model.DonationComment;
import model.SocialGroupArticle;
import model.service.AnimalManager;
import model.service.CommentManager;

public class CommentController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		final Logger log = LoggerFactory.getLogger(CommentController.class);
		
		try {
			HttpSession session = request.getSession();
			String userId = UserSessionUtils.getLoginUserId(session);
			
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			request.setAttribute("articleId", articleId);
			
			DonationComment comment = new DonationComment(0, 
					request.getParameter("com_text"),
					articleId,
					userId);
			log.debug("Comment Controller : {}", comment);
			
			CommentManager comm_man = CommentManager.getInstance();
			
			int result = comm_man.create(comment);
			System.out.println("commentInfo: " + comment);
			request.setAttribute("result", result);
			
			String category = request.getParameter("category");
			return "redirect:/donationList/"+category+"?articleId="+articleId; // viewAnimalController로 리다이렉션
		}catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception: ", e);
			return "/donationList/animalArticle.jsp";
		}
	}

}
