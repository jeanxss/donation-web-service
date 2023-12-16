package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.MyDonation;
import model.User;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class UserDonationController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 //로그인 여부 확인
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";      
        }
		
		 HttpSession session = request.getSession();
	     String userId = UserSessionUtils.getLoginUserId(session);
	     try {
	    	 	UserManager manager = UserManager.getInstance();
	    	 	List<MyDonation> donations = manager.findMyDonationList(userId);
	            request.setAttribute("donations", donations);
	            return "/user/myDonation.jsp";
	        }catch (UserNotFoundException e) {
	            // TODO: handle exception
	            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
	        }
	}

}