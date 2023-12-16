package controller.donation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.ArticleFeed;
import model.DonationArticle;
import model.service.DonationArticleManager;
import model.service.UserNotFoundException;

public class DonationArticleController  implements Controller{
   private static final Logger log = LoggerFactory.getLogger(DonationArticleController.class);
    
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      
      
      try {
         DonationArticleManager article_man = DonationArticleManager.getInstance();
         List<ArticleFeed> articleList = new ArrayList<>();
         
//         String category_feed = request.getParameter("category_feed");
//         log.debug("category_feed: {}", category_feed);
         if (request.getParameterMap().containsKey("category_feed")) {
            String category_feed = request.getParameter("category_feed");
            
            if (category_feed.equals("none")) articleList = article_man.find();
            else if (category_feed.equals("animal")) articleList = article_man.findAnimal();
            else if (category_feed.equals("disaster")) articleList = article_man.findDisaster();
            else if (category_feed.equals("socialGroup")) articleList = article_man.findSocialGroup();
            request.setAttribute("category_feed", category_feed);
         }
         else articleList = article_man.find();
            
         request.setAttribute("articleList", articleList);
         
//         List<DonationArticle> articleList = article_man.findArticle();
//         request.setAttribute("articleList", articleList);
            
         return "/donationList/donationFeed.jsp";
      }catch (ArithmeticException e) {
         // TODO: handle exception
         throw new UserNotFoundException("존재하지 않는 후원글입니다.");
      }
   }

}