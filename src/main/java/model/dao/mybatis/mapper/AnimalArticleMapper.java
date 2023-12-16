package model.dao.mybatis.mapper;

import java.util.List;

import model.AnimalArticle;
import model.DonationImage;

public interface AnimalArticleMapper {
	public int insertArticle(AnimalArticle animal);
	
	public AnimalArticle selectAnimalArticleByPk(int articleId);
	
	public int updateAnimalArticle(AnimalArticle article);
}
