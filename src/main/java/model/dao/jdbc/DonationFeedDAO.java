package model.dao.jdbc;

import java.sql.SQLException;

public class DonationFeedDAO {

	private JDBCUtil jdbcUtil = null;
	
	public DonationFeedDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	
}
