package model.service;

import java.sql.SQLException;
import java.util.List;

import model.DonationImage;
import model.SocialGroupArticle;
import model.dao.jdbc.SocialGroupDAO;
import model.dao.mybatis.SocialGroupRepository;

public class SocialGroupManager
{
    private static SocialGroupManager socialGroupMan = new SocialGroupManager();
    private SocialGroupDAO socialGroupDAO;
    private SocialGroupRepository socialGroupRepository;
    
    private SocialGroupManager() {
        try {
            socialGroupDAO = new SocialGroupDAO();
            socialGroupRepository = new SocialGroupRepository();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static SocialGroupManager getInstance() {
        return socialGroupMan;
    }
    
    public int create(SocialGroupArticle article) throws SQLException, ExistingUserException {
        return socialGroupDAO.create(article);
    }
    
    public int createImage(DonationImage image) throws SQLException, ExistingUserException {
        return socialGroupRepository.createImage(image);
    }
    
    public SocialGroupArticle findSocialGroupArticleByArticleId(int articleId) 
        throws SQLException, ArticleNotFoundException {
        SocialGroupArticle socialGroupArticle = socialGroupRepository.findSocialGroupArticle(articleId);
        if (socialGroupArticle == null) {
            throw new ArticleNotFoundException(articleId + "는 존재하지 않는 socialGroup article ID입니다.");
        }
        return socialGroupArticle;
    }
    
    public List<DonationImage> findImageList (int articleId) throws SQLException{
		return socialGroupRepository.finadImageList(articleId);
	}
    
    public int update(SocialGroupArticle article) throws SQLException{
        return socialGroupRepository.updateArticle(article);
    }

    public int remove(int articleid) throws SQLException, UserNotFoundException {
        return socialGroupRepository.remove(articleid);
    }
    
    public SocialGroupDAO getArticleDAO() {
        return this.socialGroupDAO;
    }
    
    public int update_image(DonationImage image) throws SQLException{
		return socialGroupDAO.update_image(image);
	}
    
    public int getMaxOrder(int articleId)throws SQLException{
		return socialGroupDAO.getMaxOrder(articleId);
	}
}