package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.AnimalArticle;
import model.DisasterArticle;
import model.Donation;
import model.SocialGroupArticle;
import model.User;
import model.service.AnimalManager;
import model.service.DisasterManager;
import model.service.DonationManager;
import model.service.SocialGroupManager;
import model.service.UserManager;
import model.service.UserNotFoundException;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class DonationController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("GET")) {
			HttpSession session = request.getSession();
			String userId = UserSessionUtils.getLoginUserId(session);
			System.out.println("Donor: "+userId);
			
			String articleIdStr = request.getParameter("articleId");
			String category = request.getParameter("category");
			int articleId = Integer.parseInt(articleIdStr);
			
			if(category.equals("animal")) {
				AnimalManager animalManager = AnimalManager.getInstance();
				AnimalArticle article = animalManager.findAnimalArticleByArticleId(articleId);
				request.setAttribute("article", article);
			}else if(category.equals("socialGroup")) {
				SocialGroupManager socialGroupManager = SocialGroupManager.getInstance();
				SocialGroupArticle article = socialGroupManager.findSocialGroupArticleByArticleId(articleId);
				request.setAttribute("article", article);
			}else { // 이후에 disaster로 바꾸기
				DisasterManager disasterManager = DisasterManager.getInstance();
				DisasterArticle article = disasterManager.findDisasterArticleByArticleId(articleId);
				request.setAttribute("article", article);
			}
			UserManager manager = UserManager.getInstance();
			User user = manager.findUser(userId);
			request.setAttribute("user", user);
			return "/donationList/donation.jsp";  
	    }
		
		Date now = new Date();
		String amountStr = request.getParameter("amount");
		String articleId = request.getParameter("articleId");
		String category = request.getParameter("category");
		System.out.println("category: "+category);
		try {
			Donation donation = new Donation(0,
					Integer.parseInt(amountStr),
					now,
					request.getParameter("payBankName"),
					request.getParameter("accHolder"),
					request.getParameter("accNum"),
					Integer.parseInt(amountStr)*0.01,
					Integer.parseInt(articleId),
					request.getParameter("userId"));
			DonationManager manager = DonationManager.getInstance();
			manager.create(donation);
			return "redirect:/donationList/"+category+"?articleId="+articleId;
		}catch (Exception e) {
			// TODO: handle exception
			return "redirect:/donationList/"+category+"?articleId="+articleId;
		}
	}
}