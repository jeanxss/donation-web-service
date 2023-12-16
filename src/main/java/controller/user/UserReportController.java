package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.UserReport;
import model.dao.jdbc.UserReportDAO;
import model.service.UserNotFoundException;
import model.service.UserReportManager;

public class UserReportController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		final Logger log = LoggerFactory.getLogger(UserReportController.class);
				
		try {
			HttpSession session = request.getSession();
			String reporterId = UserSessionUtils.getLoginUserId(session);
			
			UserReport report = new UserReport(0, null, reporterId, request.getParameter("reportedId"));
			log.debug("UserReport Controller : {}", report);
				
			UserReportManager reportMan = UserReportManager.getInstance();
			int result = reportMan.create(report);
			log.debug("UserReport Create : {}", result);
			
			String category = request.getParameter("category");
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			return "redirect:/donationList/"+category+"?articleId="+articleId; // viewAnimalController로 리다이렉션
			
		}catch (Exception e) {	
			// TODO: handle exception
			log.debug("Exception: ", e);
			return "/donationList/animalArticle.jsp";
		}
	}
	
}
