package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {
    		log.debug("RegisterForm Request");
			return "/user/registerForm.jsp";  
	    }	
       	
       	String address = request.getParameter("address");
       	String detailAddress = request.getParameter("detailAddress");
       	String extraAddress = request.getParameter("extraAddress");
       	String totalAddress = address +" "+ detailAddress + " " + extraAddress;

    	// POST request (회원정보가 parameter로 전송됨)
       	User user = new User(
       			request.getParameter("userId"), 
       			request.getParameter("userName"),
       			request.getParameter("password"), 
       			request.getParameter("phone"), 
       			request.getParameter("birthday"), 
       			request.getParameter("email"), 
       			totalAddress);
		
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
	        return "redirect:/homepage";	
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}

