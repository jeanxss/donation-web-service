package model.service;

import java.sql.SQLException;
import java.util.List;

import model.UserReport;
import model.dao.jdbc.UserReportDAO;

public class UserReportManager {
	
	private static UserReportManager reportMan = new UserReportManager();
	private UserReportDAO userReportDAO;
	
	private UserReportManager() {
		try {
			userReportDAO = new UserReportDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserReportManager getInstance() {
		return reportMan;
	}
	
	public int create(UserReport report) throws SQLException{
		return userReportDAO.create(report);
	}
	
	public List<UserReport> findUserReportByUserId(String reporterId) throws SQLException{
		return userReportDAO.findUserReportByUserId(reporterId);
	}
}
