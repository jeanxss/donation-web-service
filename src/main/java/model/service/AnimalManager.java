package model.service;

import java.sql.SQLException;
import java.util.List;

import model.AnimalArticle;
import model.DonationImage;
import model.dao.jdbc.AnimalDAO;
import model.dao.mybatis.AnimalRepository;

public class AnimalManager {
	private static AnimalManager animalMan = new AnimalManager();
	private AnimalDAO animalDAO;
	private AnimalRepository animalRepository;
	
	private AnimalManager() {
		try {
			animalDAO = new AnimalDAO();
			animalRepository = new AnimalRepository();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AnimalManager getInstance() {
		return animalMan;
	}
	
	public int create(AnimalArticle animal) throws SQLException{
		return animalDAO.create(animal);
	}
	
	public AnimalArticle findAnimalArticleByArticleId(int articleId) throws SQLException{
		return animalRepository.findAnimalArticle(articleId);
	}
	
	public List<DonationImage> findImageList (int articleId) throws SQLException{
		return animalRepository.finadImageList(articleId);
	}
	
	public int update(AnimalArticle animal) throws SQLException{
		return animalRepository.updateArticle(animal);
	}
	
	public int remove(int articleId) throws SQLException{
		return animalRepository.remove(articleId);
	}
	
	public int createImage(DonationImage image) throws SQLException, ExistingUserException {
	    return animalRepository.createImage(image);
	}

	public int getMaxOrder(int articleId)throws SQLException{
		return animalDAO.getMaxOrder(articleId);
	}
	
	public int update_image(DonationImage image) throws SQLException{
		return animalDAO.update_image(image);
	}
}
