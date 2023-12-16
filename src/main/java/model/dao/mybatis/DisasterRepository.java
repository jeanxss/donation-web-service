package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.AnimalArticle;
import model.DisasterArticle;
import model.DonationImage;
import model.dao.mybatis.mapper.AnimalArticleMapper;
import model.dao.mybatis.mapper.DisasterArticleMapper;
import model.dao.mybatis.mapper.DonationArticleMapper;

public class DisasterRepository {
	private SqlSessionFactory sqlSessionFactory;
	
	public DisasterRepository() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public int createImage(DonationImage image) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(DonationArticleMapper.class).insertImage(image);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		}finally {
			sqlSession.close();
		}
	}
	
	public DisasterArticle findDisasterArticle(int articleId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(DisasterArticleMapper.class).selectDisasterArticleByPk(articleId);
		}finally {
			sqlSession.close();
		}
	}
	
	public List<DonationImage> finadImageList(int articleId){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(DonationArticleMapper.class).selectImages(articleId);
		} finally {
			sqlSession.close();
		}
	}
	
	public int updateArticle(DisasterArticle article) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result1 = sqlSession.getMapper(DonationArticleMapper.class).updateArticle(article);
			int result2 = sqlSession.getMapper(DisasterArticleMapper.class).updateDisasterArticle(article);
			if (result1 <= 0 || result2 <= 0) {
				sqlSession.rollback();
			} 
			sqlSession.commit();
			return result1;
		} finally {
			sqlSession.close();
		}
	}
	
	public int remove(int articleId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			System.out.println("bybatis Delete");
			int result = sqlSession.getMapper(DonationArticleMapper.class).deleteArticle(articleId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
	}
}
