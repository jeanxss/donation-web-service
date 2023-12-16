package model;

import java.util.Date;
import java.util.List;

public class DonationReceipt {
	private int receiptId;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int articleId;
	private List<ReceiptImage> imageList;
	
	public DonationReceipt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonationReceipt(int receiptId, String content, Date createDate, Date updateDate, int articleId, List<ReceiptImage> imageList) {
		super();
		this.receiptId = receiptId;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.articleId = articleId;
		this.imageList = imageList;
	}
	
	public DonationReceipt(int receiptId, String content, Date createDate, Date updateDate, int articleId) {
		super();
		this.receiptId = receiptId;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.articleId = articleId;
	}
	
	public int getReceiptId() {
		return receiptId;
	}
	
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
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
	
	public List<ReceiptImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<ReceiptImage> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "DonationReceipt [receiptId=" + receiptId + ", content=" + content + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", articleId=" + articleId + ", imageList=" + imageList + "]";
	}
	
}
