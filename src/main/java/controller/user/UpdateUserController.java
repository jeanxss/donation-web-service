package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	
    	if (request.getMethod().equals("GET")) {
    		HttpSession session = request.getSession();
            String userId = UserSessionUtils.getLoginUserId(session);
    		
    		UserManager manager = UserManager.getInstance();
			User user = manager.findUser(userId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("user", user);			    		
    		return "/user/update.jsp"; 
	    }	
    	
    	String address = request.getParameter("address");
       	String detailAddress = request.getParameter("detailAddress");
       	String extraAddress = request.getParameter("extraAddress");
       	String totalAddress = address +" "+ detailAddress + " " + extraAddress;
     	
       	User user = new User(
       			request.getParameter("userId"), 
       			request.getParameter("password"), 
       			request.getParameter("phone"), 
       			request.getParameter("email"), 
       			totalAddress);
       	
       	UserManager manager = UserManager.getInstance();
       	manager.update(user);
       	request.setAttribute("user", user);
       	
    	return "redirect:/user/myInfo"; 
    }
}