package model;

public class Donator {
	private int amount;
	private String userId;
	
	public Donator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Donator(int amount, String userId) {
		super();
		this.amount = amount;
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}