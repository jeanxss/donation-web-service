package model;

public class ArticleFeed {
	private int articleId;
	private String title;
	private String category;
    private String fileName;
    
	public ArticleFeed() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArticleFeed(int articleId, String title, String category, String fileName) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.category = category;
		this.fileName = fileName;
	}
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "ArticleFeed [articleId=" + articleId + ", title=" + title + ", category=" + category + ", fileName="
				+ fileName + "]";
	}

}
