package model.dao.mybatis.mapper;

import model.SocialGroupArticle;

public interface SocialGroupArticleMapper {
	public SocialGroupArticle selectSocialGroupArticleByPk(int articleId);
	
	public int updateSocialGroupArticle(SocialGroupArticle article);
}
