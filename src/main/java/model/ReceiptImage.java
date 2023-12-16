package model;

public class ReceiptImage
{
    String imgLink;
    int imgOrder;
    int receiptId;
    DonationReceipt receipt;
    
    public ReceiptImage() {

    }

    public ReceiptImage(String imgLink, int imgOrder, int receiptId, DonationReceipt receipt) {
        this.imgLink = imgLink;
        this.imgOrder = imgOrder;
        this.receiptId = receiptId;
        this.receipt = receipt;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(int imgOrder) {
        this.imgOrder = imgOrder;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

	public DonationReceipt getReceipt() {
		return receipt;
	}

	public void setReceipt(DonationReceipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "ReceiptImage [imgLink=" + imgLink + ", imgOrder=" + imgOrder + ", receiptId=" + receiptId + ", receipt="
				+ receipt + "]";
	}
    
    
    
}