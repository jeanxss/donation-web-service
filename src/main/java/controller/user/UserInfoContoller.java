package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class UserInfoContoller implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//로그인 여부 확인
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		
        }
		
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		
		try {
			UserManager manager = UserManager.getInstance();
			User user = manager.findUser(userId);
			request.setAttribute("user", user);
			return "/user/myInfo.jsp";
		}catch (UserNotFoundException e) {
			// TODO: handle exception
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		
	}

}
