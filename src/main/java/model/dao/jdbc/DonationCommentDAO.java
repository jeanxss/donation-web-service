package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DonationComment;

public class DonationCommentDAO {
private JDBCUtil jdbcUtil = null;
	
	public DonationCommentDAO() {
		jdbcUtil = new JDBCUtil();
	}

	//	댓글 작성 시, DonationComment table에 튜플 삽입, 해당 댓글에 대한 DonationComment 객체 입력
	public int create(DonationComment comment) throws SQLException {
		try {
			String insert = "INSERT INTO DONATION_COMMENT VALUES (seq_comment_id.nextval, ?, SYSDATE, NULL, ?, ?)";		
			Object[] param = new Object[] {comment.getContent(), comment.getArticleId(), comment.getUserId()};
			
			jdbcUtil.setSqlAndParameters(insert, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	//	articleId로 후원글에 대한 모든 댓글 찾기, articleId로 질의(입력)
	public List<DonationComment> findAllComment(int articleId) {
		try {
			String query = "SELECT comment_id, content, create_date, update_date, article_id, user_id "
					+ "FROM DONATION_COMMENT "
					+ "WHERE article_id = ? "
					+ "ORDER BY create_date desc";
	    	
	    	Object[] param = new Object[] {articleId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			List<DonationComment> list = new ArrayList<DonationComment>();
			
			while (rs.next()) {
				DonationComment comment = new DonationComment();
				
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setContent(rs.getString("content"));
				comment.setCreateDate(rs.getDate("create_date"));
				comment.setUpdateDate(rs.getDate("update_date"));
				comment.setArticleId(rs.getInt("article_id"));
				comment.setUserId(rs.getString("user_id"));
				
				list.add(comment);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	// 페이지 번호와 페이지 당 출력할 댓글의 수를 입력받아 해당 페이지의 댓글들을 찾기, currentPage, countPerPage, articleId로 질의(입력)
	public List<DonationComment> findPageCommentList(int currentPage, int countPerPage, int articleId) {
		try {
			String query = "SELECT comment_id, content, create_date, update_date, article_id, user_id "
					+ "FROM DONATION_COMMENT "
					+ "WHERE article_id = ? "
					+ "ORDER BY create_date desc";
			Object[] param = new Object[] {articleId};
			
			jdbcUtil.setSqlAndParameters(query, param,					// JDBCUtil에 query문 설정
					ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
					ResultSet.CONCUR_READ_ONLY);
	    	
			
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<DonationComment> list = new ArrayList<DonationComment>();	// User들의 리스트 생성
				
				do {
					DonationComment comment = new DonationComment();
					
					comment.setCommentId(rs.getInt("comment_id"));
					comment.setContent(rs.getString("content"));
					comment.setCreateDate(rs.getDate("create_date"));
					comment.setUpdateDate(rs.getDate("update_date"));
					comment.setArticleId(rs.getInt("article_id"));
					comment.setUserId(rs.getString("user_id"));
					
					list.add(comment);
				} while ((rs.next()) && (--countPerPage > 0));		
				return list;	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	//	댓글 수정, id나 createDate는 수정되지 않고 content, updateDate만 수정, 수정한 DonationComment 객체 입력
	public int update(int commentId, String content) throws SQLException {
		try {
			String update = "UPDATE DONATION_COMMENT SET content = ?, update_date = sysdate WHERE comment_id = ?";		
			Object[] param = new Object[] {content, commentId};
			
			jdbcUtil.setSqlAndParameters(update, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	//	댓글 1개 삭제, comment_id로 삭제(입력)
	public int remove(int commentId) throws SQLException {
		try {
			String remove = "DELETE FROM DONATION_COMMENT WHERE comment_id = ?";		
			Object[] param = new Object[] {commentId};
			
			jdbcUtil.setSqlAndParameters(remove, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	//	후원 인증글 내 댓글들 삭제, donation_article이 삭제되면 같이 삭제되므로 article_id로 삭제(입력)
	public int removeAllComment(int articleId) throws SQLException {
		try {
			String remove = "DELETE FROM DONATION_COMMENT WHERE article_id = ?";		
			Object[] param = new Object[] {articleId};
			
			jdbcUtil.setSqlAndParameters(remove, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}

}
