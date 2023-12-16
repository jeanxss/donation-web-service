package model;

public class MyDonation {
	private String title;
	private String writer;
	private int amount;
	private String category;
	private int articleId;
	
	public MyDonation(String title, String writer, int amount, String category, int articleId) {
		this.title = title;
		this.writer = writer;
		this.amount = amount;
		this.category = category;
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
}