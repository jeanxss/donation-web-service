package model.service;

import java.sql.SQLException;
import java.util.List;

import model.ArticleFeed;
import model.DonationArticle;
import model.DonationImage;
import model.dao.jdbc.DonationArticleDAO;

public class DonationArticleManager {
	private static DonationArticleManager articleMan = new DonationArticleManager();
	private DonationArticleDAO articleDAO;
	
	private DonationArticleManager() {
		try {
			articleDAO = new DonationArticleDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DonationArticleManager getInstance() {
		return articleMan;
	}
	
	public List<ArticleFeed> find() throws SQLException {
		return articleDAO.find();
	}
	
	public List<DonationArticle> findArticle() throws SQLException {
		return articleDAO.findArticle();
	}
	
	public List<ArticleFeed> findAnimal() throws SQLException {
		return articleDAO.findAnimal();
	}
	
	public List<ArticleFeed> findDisaster() throws SQLException {
		return articleDAO.findDisaster();
	}
	
	public List<ArticleFeed> findSocialGroup() throws SQLException {
		return articleDAO.findSocialGroup();
	}

}
