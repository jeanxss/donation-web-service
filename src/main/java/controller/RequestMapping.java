package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.donation.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/homepage", new ForwardController("/homepage.jsp"));
        mappings.put("/donationList/donationFeed", new DonationArticleController()); // 1123 by 채연
        
        //mypage
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/myInfo", new UserInfoContoller());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/myArticle", new UserArticleController());
        mappings.put("/user/myDonaion", new UserDonationController());
        //1126 by 채연
        mappings.put("/user/report", new UserReportController());

        //Feed 에서 idCard from으로
        mappings.put("/donationForm/idCard", new IdCardController());
        
        //donation Article Create
        mappings.put("/donationForm/animal", new AnimalArticleController());
        mappings.put("/donationForm/disaster", new DisasterArticleController());
        mappings.put("/donationForm/socialGroup", new SocialGroupArticleController());
        //Feed 에서 idCard from으로, 1127 by 채연
        mappings.put("/donationForm/idCard", new IdCardController());


        // donation Article View
        mappings.put("/donationList/animal", new ViewAnimalArticleController()); // 1123 by 채연, [22/11/26]update by 나현
        mappings.put("/donationList/socialGroup", new ViewSocialGroupArticleController());
        mappings.put("/donationList/disaster", new ViewDisasterController()); // [22/11/29] by 나현
        
        //donation Article Update
        mappings.put("/donationForm/animalArticleUpdate", new UpdateAnimalArticleController());
        mappings.put("/donationForm/disasterArticleUpdate", new UpdateDisasterArticleController());
        mappings.put("/donationForm/socialGroupArticleUpdate", new UpdateSocialGroupArticleController());
        
        //[22/11/26] donaiton by 나현 
        mappings.put("/donation", new DonationController());

        
        // comment controller // 1123 by 채연
        mappings.put("/donationList/comment", new CommentController());
        mappings.put("/donationList/commentDelete", new DeleteCommentController());
        mappings.put("/donationList/commentUpdate", new UpdateCommentController());
        
        //doantion Article Delete
        mappings.put("/donationList/socialGroupArticleDelete", new DeleteSocialGroupArticleController());
        mappings.put("/donationList/animalArticleDelete", new DeleteAnimalArticleController());
        mappings.put("/donationList/disasterArticleDelete", new DeleteDisasterArticleController());
        
        // donation Receipt 1127 by 채연
        mappings.put("/donationList/receipt", new DonationReceiptController());
        mappings.put("/donationForm/receipt", new ForwardController("/donationForm/receiptForm.jsp"));
        mappings.put("/donationList/receiptDelete", new DeleteDonationReceiptController());
        mappings.put("/donationForm/receiptUpdate", new UpdateDonationReceiptController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}