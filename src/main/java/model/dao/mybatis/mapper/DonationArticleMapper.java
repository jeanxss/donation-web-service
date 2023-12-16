package model.dao.mybatis.mapper;

import java.util.List;

import model.DisasterArticle;
import model.DonationArticle;
import model.DonationImage;

public interface DonationArticleMapper {
	public int insertImage(DonationImage image);
	
	public List<DonationImage> selectImages(int articleId);
	
	public int updateArticle(DonationArticle article);
	
	public int deleteArticle(int articleId);
}
