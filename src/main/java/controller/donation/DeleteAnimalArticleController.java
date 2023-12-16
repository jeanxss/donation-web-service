package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.AnimalManager;
import model.service.SocialGroupManager;

public class DeleteAnimalArticleController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		 HttpSession session = request.getSession(); 
         if (UserSessionUtils.isLoginUser(userId, session)) { //로그인한 사용자가 userId인 경우
             AnimalManager manager = AnimalManager.getInstance();
             manager.remove(articleId);
             return "redirect:/donationList/donationFeed";
         } else { //로그인한 사용자가 userId가 아닌 경우
             request.setAttribute("deleteFailed", true);
             String msg = "타인의 article은 삭제할 수 없습니다.";
             request.setAttribute("exception", new IllegalStateException(msg));            
             return "/donationList/animalArticle.jsp";
         }
	}
	
}
