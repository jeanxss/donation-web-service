package model.service;

import java.sql.SQLException;
import java.util.List;

import model.DonationReceipt;
import model.ReceiptImage;
import model.dao.jdbc.DonationReceiptDAO;

public class DonationReceiptManager {
	private static DonationReceiptManager receiptMan = new DonationReceiptManager();
	private DonationReceiptDAO receiptDAO;
	
	private DonationReceiptManager() {
		try {
			receiptDAO = new DonationReceiptDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static DonationReceiptManager getInstance() {
		return receiptMan;
	}
	
	public int create(DonationReceipt receipt) throws SQLException {
		return receiptDAO.create(receipt);
	}
	
	public int create_image(ReceiptImage receiptImage) throws SQLException {
		return receiptDAO.create_image(receiptImage);
	}
	
	public int update_image(ReceiptImage receiptImage) throws SQLException {
		return receiptDAO.update_image(receiptImage);
	}
	
	public DonationReceipt findByReceiptId(int receiptId) {
		return receiptDAO.findByReceiptId(receiptId);
	}
	
	public List<ReceiptImage> findImageByReceiptId(int receiptId) {
		return receiptDAO.findImageByReceiptId(receiptId);
	}
	
	public DonationReceipt findByArticleId(int articleId) {
		return receiptDAO.findByArticleId(articleId);
	}
	
	public int update(DonationReceipt receipt) throws SQLException {
		return receiptDAO.update(receipt);
	}
	
	public int remove(int articleId, int receiptId) throws SQLException {
		return receiptDAO.remove(articleId, receiptId);
	}
	
	public int getMaxOrder(int articleId)throws SQLException{
		return receiptDAO.getMaxOrder(articleId);
	}
}
