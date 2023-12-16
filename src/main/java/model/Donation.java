package model;

import java.util.Date;

public class Donation {
	private int donationId;
	private int amount;
	private Date donationDate;
	private String payBankName;
	private String payUserName;
	private String payCardNum;
	private int articleId;
	private double reward;
	private String userId;
	
	public Donation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Donation(int donationId, int amount, Date donationDate, String payBankName, String payUserName,
			String payCardNum, double reward, int articleId, String userId) {
		super();
		this.donationId = donationId;
		this.amount = amount;
		this.donationDate = donationDate;
		this.payBankName = payBankName;
		this.payUserName = payUserName;
		this.payCardNum = payCardNum;
		this.reward = reward;
		this.articleId = articleId;
		this.userId = userId;
	}

	public int getDonationId() {
		return donationId;
	}

	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	public String getPayCardNum() {
		return payCardNum;
	}

	public void setPayCardNum(String payCardNum) {
		this.payCardNum = payCardNum;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", amount=" + amount + ", donationDate=" + donationDate
				+ ", payBankName=" + payBankName + ", payUserName=" + payUserName + ", payCardNum=" + payCardNum
				+ ", articleId=" + articleId + ", userId=" + userId + "]";
	}
	
	
}
