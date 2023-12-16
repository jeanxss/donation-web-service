package model.service;

import java.sql.SQLException;
import java.util.List;

import model.DonationComment;
import model.dao.jdbc.DonationCommentDAO;

public class CommentManager {
	private static CommentManager commentMan = new CommentManager();
	private DonationCommentDAO commentDAO;
	
	private CommentManager() {
		try {
			commentDAO = new DonationCommentDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CommentManager getInstance() {
		return commentMan;
	}
	
	public int create(DonationComment comment) throws SQLException {
		return commentDAO.create(comment);
	}
	
	public List<DonationComment> findAllComment(int articleId) throws SQLException {
		return commentDAO.findAllComment(articleId);
	}
	
	
	public List<DonationComment> findPageCommentList(int currentPage, int countPerPage, int articleId)  throws SQLException {
		return commentDAO.findPageCommentList(currentPage, countPerPage, articleId);
	}
	
	
	public int update(int commentId, String content) throws SQLException {
		return commentDAO.update(commentId, content);
	}
	
	
	public int remove(int commentId) throws SQLException {
		return commentDAO.remove(commentId);
	}
	
	public int removeAllComment(int articleId) throws SQLException {
		return commentDAO.removeAllComment(articleId);
	}
	
}
