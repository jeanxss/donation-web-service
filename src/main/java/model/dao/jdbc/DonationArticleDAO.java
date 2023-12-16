package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.Image;

import model.AnimalArticle;
import model.ArticleFeed;
import model.DonationArticle;
import model.DonationComment;
import model.DonationImage;

public class DonationArticleDAO {
private JDBCUtil jdbcUtil = null;
	
	public DonationArticleDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<ArticleFeed> find() throws SQLException{
		
		String sql = "SELECT a.article_id, title, category, img_link "
				+ "FROM donation_article a JOIN donation_image d ON a.article_id = d.article_id "
				+"WHERE img_order = 1 ORDER BY create_date desc";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ArticleFeed> list = new ArrayList<ArticleFeed>();
			
			while (rs.next()) {
				ArticleFeed articleFeed = new ArticleFeed(
						rs.getInt("article_id"),
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getString("img_link"));
				list.add(articleFeed);
			}
			return list;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	public List<DonationArticle> findArticle() throws SQLException{
			
			String sql = "SELECT article_id, title, category, deadline, bank_name, acc_holder, acc_num, due_date, "
					+ "use_plan, other_text, create_date, update_date, receipt_check, user_id, total_amount "
					+ "FROM donation_article "
					+"ORDER BY create_date desc";
			jdbcUtil.setSqlAndParameters(sql, null);
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<DonationArticle> list = new ArrayList<DonationArticle>();
				int articleId;
				while (rs.next()) {
					DonationArticle article = new DonationArticle(
							rs.getInt("article_id"), 
							rs.getString("title"), 
							rs.getString("category"), 
							rs.getString("deadline"), 
							rs.getString("bank_name"), 
							rs.getString("acc_holder"), 
							rs.getString("acc_num"),
							rs.getString("due_date"),
							rs.getString("use_plan"), 
							rs.getString("other_text"),
							rs.getDate("create_date"), 
							rs.getDate("update_date"), 
							rs.getString("receipt_check"),
							rs.getString("user_id"),   
							rs.getInt("total_amount"));
					list.add(article);
				}
				return list;
			}catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 반환
			}	
			return null;
		}
	
	public List<ArticleFeed> findAnimal() throws SQLException{
		
		String sql = "SELECT a.article_id, title, category, img_link "
				+ "FROM donation_article a JOIN donation_image d ON a.article_id = d.article_id "
				+"WHERE img_order = 1 and category = 'animal' ORDER BY create_date desc";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ArticleFeed> list = new ArrayList<ArticleFeed>();
			
			while (rs.next()) {
				ArticleFeed articleFeed = new ArticleFeed(
						rs.getInt("article_id"),
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getString("img_link"));
				list.add(articleFeed);
			}
			return list;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}

	public List<ArticleFeed> findDisaster() throws SQLException{
		
		String sql = "SELECT a.article_id, title, category, img_link "
				+ "FROM donation_article a JOIN donation_image d ON a.article_id = d.article_id "
				+"WHERE img_order = 1 and category = 'disaster' ORDER BY create_date desc";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ArticleFeed> list = new ArrayList<ArticleFeed>();
			
			while (rs.next()) {
				ArticleFeed articleFeed = new ArticleFeed(
						rs.getInt("article_id"),
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getString("img_link"));
				list.add(articleFeed);
			}
			return list;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	public List<ArticleFeed> findSocialGroup() throws SQLException{
		
		String sql = "SELECT a.article_id, title, category, img_link "
				+ "FROM donation_article a JOIN donation_image d ON a.article_id = d.article_id "
				+"WHERE img_order = 1 and category = 'socialGroup' ORDER BY create_date desc";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ArticleFeed> list = new ArrayList<ArticleFeed>();
			
			while (rs.next()) {
				ArticleFeed articleFeed = new ArticleFeed(
						rs.getInt("article_id"),
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getString("img_link"));
				list.add(articleFeed);
			}
			return list;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}

}
