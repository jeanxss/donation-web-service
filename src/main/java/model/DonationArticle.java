package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class DonationArticle implements Serializable{
	private int articleId;
	private String title;
	private String category;
	private String deadline; // 후원글 마감일
	private String bankName;
	private String accHolder;
	private String accNum;
	private String idCheck;
	private String dueDate; // 사용 마감일
	private String usePlan;
	private String otherText;
	private Date createDate;
	private Date updateDate;
	private String receiptCheck;
	private String userId;
	private int totalAmount;
	DonationImage donationImage;
	private List<DonationImage> imageList;
	
	public DonationArticle() {};

	public DonationArticle(int articleId, String title, String category, String deadline, String bankName,
			String accHolder, String accNum, String idCheck, String dueDate, String usePlan, String otherText,
			Date createDate, Date updateDate, String receiptCheck, int totalAmount, String userId) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.category = category;
		this.deadline = deadline;
		this.bankName = bankName;
		this.accHolder = accHolder;
		this.accNum = accNum;
		this.idCheck = idCheck;
		this.dueDate = dueDate;
		this.usePlan = usePlan;
		this.otherText = otherText;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.receiptCheck = receiptCheck;
		this.totalAmount = totalAmount;
		this.userId = userId;
	}
	
	public DonationArticle(int articleId, String title, String category, String deadline, String bankName,
			String accHolder, String accNum, String dueDate, String usePlan, String otherText,
			Date createDate, Date updateDate, String receiptCheck, String userId, int totalAmount) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.category = category;
		this.deadline = deadline;
		this.bankName = bankName;
		this.accHolder = accHolder;
		this.accNum = accNum;
		this.dueDate = dueDate;
		this.usePlan = usePlan;
		this.otherText = otherText;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.receiptCheck = receiptCheck;
		this.userId = userId;
		this.totalAmount = totalAmount;
	}
	
	public DonationArticle(int articleId, String title, String category, String deadline, 
			Date createDate, Date updateDate, int totalAmount, String userId) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.category = category;
		this.deadline = deadline;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.totalAmount = totalAmount;
		this.userId = userId;
	}
	
	public DonationArticle(int article_id, String title, String category, Date createDate, int totalAmount, String receiptCheck, String userId) {
		this.articleId = article_id;
		this.title = title;
		this.category = category;
		this.createDate = createDate;
		this.receiptCheck = receiptCheck;
		this.totalAmount = totalAmount;
		this.userId = userId;
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

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(String idCheck) {
		this.idCheck = idCheck;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getUsePlan() {
		return usePlan;
	}

	public void setUsePlan(String usePlan) {
		this.usePlan = usePlan;
	}

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
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

	public String getReceiptCheck() {
		return receiptCheck;
	}

	public void setReceiptCheck(String receiptCheck) {
		this.receiptCheck = receiptCheck;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public List<DonationImage> getImageList() {
        return imageList;
    }
	
    public void setImageList(List<DonationImage> imageList) {
        this.imageList = imageList;
    }
    
    public DonationImage getDonationImage() {
		return donationImage;
	}

	public void setDonationImage(DonationImage donationImage) {
		this.donationImage = donationImage;
	}

	@Override
	public String toString() {
		return "DonationArticle [articleId=" + articleId + ", title=" + title + ", category=" + category + ", deadline="
				+ deadline + ", bankName=" + bankName + ", accHolder=" + accHolder + ", accNum=" + accNum + ", idCheck="
				+ idCheck + ", dueDate=" + dueDate + ", usePlan=" + usePlan + ", otherText=" + otherText
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", receiptCheck=" + receiptCheck
				+ ", userId=" + userId + ", totalAmount=" + totalAmount + "]";
	}
}