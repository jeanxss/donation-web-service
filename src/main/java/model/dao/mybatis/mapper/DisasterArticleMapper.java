package model.dao.mybatis.mapper;

import java.util.List;

import model.DisasterArticle;
import model.DonationImage;

public interface DisasterArticleMapper {
	
	public DisasterArticle selectDisasterArticleByPk(int articleId);
	
	public int updateDisasterArticle(DisasterArticle article);
}
