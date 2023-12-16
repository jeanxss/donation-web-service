package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.SocialGroupArticle;
import model.User;
import model.service.SocialGroupManager;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class DeleteSocialGroupArticleController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteSocialGroupArticleController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            //socialGroupArticle.jsp에서 넘긴 값
            String userId = request.getParameter("userId");
            log.debug("userId : {}", userId);
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            log.debug("articleId : {}", articleId);
            
            HttpSession session = request.getSession(); 
            if (UserSessionUtils.isLoginUser(userId, session)) { //로그인한 사용자가 userId인 경우
                SocialGroupManager manager = SocialGroupManager.getInstance();
                manager.remove(articleId);
                return "redirect:/donationList/donationFeed";
            } else { //로그인한 사용자가 userId가 아닌 경우
                request.setAttribute("deleteFailed", true);
                String msg = "타인의 article은 삭제할 수 없습니다.";
                request.setAttribute("exception", new IllegalStateException(msg));            
                return "/donationList/socialGroupArticle.jsp";
            }
         
    }

}
