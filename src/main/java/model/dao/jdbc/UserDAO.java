package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DonationArticle;
import model.Donator;
import model.MyDonation;
import model.User;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO USER_INFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getName(),
				user.getPassword(), user.getPhoneNum(), user.getBirthday(), 
				user.getEmail(), user.getAddress(), 0, 0};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	/**
	 * 기존의 사용자 정보를 수정.
	 */
	public int update(User user) throws SQLException {
		String sql = "UPDATE USER_INFO "
					+ "SET pwd=?, phone_num=?, email=?, address = ? "
					+ "WHERE user_id=?";
		Object[] param = new Object[] {user.getPassword(), user.getPhoneNum(),
				user.getEmail(), user.getAddress(), user.getUserId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM USER_INFO WHERE userid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public User findUser(String userId) throws SQLException {
        String sql = "SELECT name, pwd, phone_num, TO_CHAR(BIRTHDAY, 'yyyy-MM-DD') \"birthday\", email, address, reward_amount, report_num "
        			+ "FROM USER_INFO "
        			+ "WHERE user_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// user 정보 발견
				// User 객체를 생성하여 user 정보를 저장
				User user = new User(
					userId,
					rs.getString("name"),
					rs.getString("pwd"),
					rs.getString("phone_num"),
					rs.getString("birthday"),
					rs.getString("email"),
					rs.getString("address"),
					rs.getInt("reward_amount"),
					rs.getInt("report_num")
				);
				return user;	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	// 사용자가 작성한 글의 리스트
	public List<DonationArticle> findMyArticleList(String userId) throws SQLException{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT article_id, title, category, TO_CHAR(create_date, 'YYYY-MM-DD') create_date, total_amount, receipt_check ");
		sb.append("FROM donation_article ");
		sb.append("WHERE user_id = ? ");
		String sql = sb.toString();
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		List<DonationArticle> list = new ArrayList<DonationArticle>();
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			while(rs.next()) {
				DonationArticle donation = new DonationArticle(
						rs.getInt("article_id"),
						rs.getString("title"),
						rs.getString("category"),
						rs.getDate("create_date"),
						rs.getInt("total_amount"),
						rs.getString("receipt_check"),
						userId
						);
				list.add(donation);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	public List<MyDonation> findMyDonationList(String userId) throws SQLException{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT a.title AS \"title\", a.user_id AS \"user_id\", d.amount AS \"amount\", a.category AS \"category\", a.article_id AS \"article_id\" ");
		sb.append("FROM donation d JOIN donation_article a on d.article_id = a.article_id ");
		sb.append("WHERE d.user_id = ? ");
		String sql = sb.toString();
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		List<MyDonation> list = new ArrayList<MyDonation>();
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			while(rs.next()) {
				MyDonation donation = new MyDonation(
						rs.getString("title"),
						rs.getString("user_id"),
						rs.getInt("amount"),
						rs.getString("category"),
						rs.getInt("article_id")
						);
				list.add(donation);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
		
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM USER_INFO WHERE user_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

}