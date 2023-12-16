package model.service;

import java.sql.SQLException;
import java.util.List;

import model.AnimalArticle;
import model.Donation;
import model.DonationComment;
import model.Donator;
import model.dao.jdbc.AnimalDAO;
import model.dao.jdbc.DonationDAO;

public class DonationManager {
	private static DonationManager donationMan = new DonationManager();
	private DonationDAO donationDAO;
	
	private DonationManager() {
		try {
			donationDAO = new DonationDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DonationManager getInstance() {
		return donationMan;
	}
	
	public int create(Donation donation) throws SQLException{
		return donationDAO.create(donation);
	}
	
	public List<Donator> latest10Donors(int articleId) throws SQLException {
		return donationDAO.findDonation10List(articleId);
	}
}