package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Donator;
import model.Donation;

public class DonationDAO {
	private JDBCUtil jdbcUtil = null;
	
	public DonationDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//	후원 입금마다 donation table에 튜플 삽입, 후원에 대한 donation 객체 입력
	public int create(Donation donation) throws SQLException {
		try {
			String insert = "INSERT INTO DONATION VALUES (seq_donation_id.nextval, ?, SYSDATE, ?, ?, ?, ?, ?)";		
			Object[] param1 = new Object[] {donation.getAmount(), donation.getPayBankName(), 
					donation.getPayUserName(), donation.getPayCardNum(), 
					donation.getArticleId(), donation.getUserId()};
			
			jdbcUtil.setSqlAndParameters(insert, param1);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
			
			String update = "UPDATE DONATION_ARTICLE SET total_amount = total_amount + ? WHERE article_id = ?";		
			Object[] param2 = new Object[] {donation.getAmount(), donation.getArticleId()};
			
			jdbcUtil.setSqlAndParameters(update, param2);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
			String update2 = "UPDATE USER_INFO SET reward_amount = reward_amount + ? WHERE user_id = ?";
			jdbcUtil.setSqlAndParameters(update2, new Object[] {donation.getReward(), donation.getUserId()});
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
	
	//	각 후원글에 포함되어있는 후원 내역 테이블을 위한 최근 후원한 10명 찾기, articleId로 질의(입력)
	public List<Donator> findDonation10List(int articleId) {
		try {
			String query = "SELECT user_id, amount "
					+ "FROM (SELECT user_id, amount FROM DONATION WHERE article_id = ? ORDER BY donation_date desc)"
					+ "WHERE ROWNUM <= 10";
	    	
	    	Object[] param = new Object[] {articleId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			List<Donator> list = new ArrayList<Donator>();
			
			while (rs.next()) {
				Donator donator = new Donator();
				donator.setUserId(rs.getString("user_id"));
				donator.setAmount(rs.getInt("amount"));
				
				list.add(donator);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
		
	//	각 후원글에 대한 전체 후원 내역 찾기, articleId로 질의(입력)
	public List<Donation> findAllDonation(int articleId) {
		try {
			String query = "SELECT donation_id, amount, donation_date, pay_bank_name, pay_user_name, "
					+ "pay_card_num, article_id, user_id FROM DONATION "
					+ "WHERE article_id = ? ORDER BY donation_date desc ";
	    	
	    	Object[] param = new Object[] {articleId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			List<Donation> list = new ArrayList<Donation>();
			
			while (rs.next()) {
				Donation donation = new Donation();
				
				donation.setDonationId(rs.getInt("donation_id"));
				donation.setAmount(rs.getInt("amount"));
				donation.setDonationDate(rs.getDate("donation_date"));
				donation.setPayBankName(rs.getString("pay_bank_name"));
				donation.setPayUserName(rs.getString("pay_user_name"));
				donation.setPayCardNum(rs.getString("pay_card_num"));
				donation.setArticleId(rs.getInt("article_id"));
				donation.setUserId(rs.getString("user_id"));
				
				list.add(donation);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}

}