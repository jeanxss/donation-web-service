package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserReport;


public class UserReportDAO {
private JDBCUtil jdbcUtil = null;
	
	public UserReportDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//	신고 시, 해당 유저에게 신고, UserReport table에 튜플 삽입, 신고에 대한 UserReport 객체 입력
	// User table의 reportNum도 1 증가 - update
	public int create(UserReport report) throws SQLException {
		try {
			String insert = "INSERT INTO user_report VALUES (seq_user_report_id.nextval, SYSDATE, ?, ?)";		
			Object[] param1 = new Object[] {report.getReporterId(), report.getReportedId()};
			
			jdbcUtil.setSqlAndParameters(insert, param1);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
			
			String update = "UPDATE user_info SET report_num = report_num + 1 WHERE user_id = ?";		
			Object[] param2 = new Object[] {report.getReportedId()};
			
			jdbcUtil.setSqlAndParameters(update, param2);	// JDBCUtil 에 insert문과 매개 변수 설정
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
		
	//	각 유저에 대한 모든 신고 내역 찾기, 해당 유저가 신고한 내역, reporter_id로 질의(입력)
	public List<UserReport> findUserReportByUserId(String reporterId) {
		try {
			String query = "SELECT report_id, report_date, reporter_id, reported_id "
					+ "FROM user_report "
					+ "WHERE reporter_id = ? ORDER BY report_date desc ";
	    	
	    	Object[] param = new Object[] {reporterId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			List<UserReport> list = new ArrayList<UserReport>();
			
			while (rs.next()) {
				UserReport report = new UserReport();
				
				report.setReportId(rs.getInt("report_id"));
				report.setReportDate(rs.getDate("report_date"));
				report.setReporterId(rs.getString("reporter_id"));
				report.setReportedId(rs.getString("reported_id"));
				
				list.add(report);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}

}
