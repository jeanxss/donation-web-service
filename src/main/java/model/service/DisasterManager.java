package model.service;

import java.sql.SQLException;
import java.util.List;

import model.DisasterArticle;
import model.DonationImage;
import model.dao.jdbc.DisasterDAO;
import model.dao.mybatis.DisasterRepository;

public class DisasterManager {
	private static DisasterManager disasterMan = new DisasterManager();
	private DisasterDAO disasterDAO;
	private DisasterRepository disasterRepository;
	
	private DisasterManager() {
		try {
			disasterDAO = new DisasterDAO();
			disasterRepository = new DisasterRepository();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DisasterManager  getInstance() {
		return disasterMan;
	}
	
	public int create(DisasterArticle disaster)throws SQLException{
		return disasterDAO.create(disaster);
	}
	
	public int createImage(DonationImage image) throws SQLException, ExistingUserException {
	    return disasterRepository.createImage(image);
	}
	
	public DisasterArticle findDisasterArticleByArticleId(int articleId) throws SQLException{
		return disasterRepository.findDisasterArticle(articleId);
	}
	
	public List<DonationImage> findImageList (int articleId) throws SQLException{
		return disasterRepository.finadImageList(articleId);
	}
	
	public int update(DisasterArticle disaster) throws SQLException{
		return disasterRepository.updateArticle(disaster);
	}
	
	public int remove(int articleId) throws SQLException{
		return disasterRepository.remove(articleId);
	}
	
	public int update_image(DonationImage image) throws SQLException{
		return disasterDAO.update_image(image);
	}
	
	public int getMaxOrder(int articleId)throws SQLException{
		return disasterDAO.getMaxOrder(articleId);
	}
}
