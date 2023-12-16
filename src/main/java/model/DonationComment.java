package model;

import java.util.Date;

public class DonationComment {
	private int commentId;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int articleId;
	private String userId;
	
	public DonationComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonationComment(int commentId, String content, Date createDate, Date updateDate, int articleId,
			String userId) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.articleId = articleId;
		this.userId = userId;
	}
	
	public DonationComment(int commentId, String content,  int articleId, String userId) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.articleId = articleId;
		this.userId = userId;
	}


	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	@Override
	public String toString() {
		return "DonationComment [commentId=" + commentId + ", content=" + content + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", articleId=" + articleId + ", userId=" + userId + "]";
	}
	
	
	
}
