package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class DisasterArticle extends DonationArticle{
   //DisasterDto
   private String type;
   private String name;
   private String area;
   private int damageAmount;
   private String situation;
   
   public DisasterArticle() {}
   
   public DisasterArticle(int articleId, String title, String category, String deadline, String bankName,
	         String accHolder, String accNum, String idCheck, String dueDate, String usePlan, String otherText,
	         Date createDate, Date updateDate, String receiptCheck, String userId, int totalAmount, String type,
	         String name, String area, int damageAmount, String situation) {
	      
	      super(articleId, title, category, deadline, bankName, accHolder, accNum, idCheck, dueDate, usePlan, otherText,
	            createDate, updateDate, receiptCheck, totalAmount, userId);
	      
	      this.type = type;
	      this.name = name;
	      this.area = area;
	      this.damageAmount = damageAmount;
	      this.situation = situation;
	   }

	   public DisasterArticle(int articleId, String title, String category, String deadline, String bankName,
	         String accHolder, String accNum, String dueDate, String usePlan, String otherText, Date createDate,
	         Date updateDate, String receiptCheck, String userId, int totalAmount, String type, String name,
	         String area, int damageAmount, String situation) {
	      
	      super(articleId, title, category, deadline, bankName, accHolder, accNum, dueDate, usePlan, otherText,
	            createDate, updateDate, receiptCheck, userId, totalAmount);
	      
	      this.type = type;
	      this.name = name;
	      this.area = area;
	      this.damageAmount = damageAmount;
	      this.situation = situation;
	   }
	   
   
   public String getType() {
      return type;
   }
   
   public void setType(String type) {
      this.type = type;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getArea() {
      return area;
   }
   
   public void setArea(String area) {
      this.area = area;
   }
   
   public int getDamageAmount() {
      return damageAmount;
   }
   
   public void setDamageAmount(int damageAmount) {
      this.damageAmount = damageAmount;
   }
   
   public String getSituation() {
      return situation;
   }
   
   public void setSituation(String situation) {
      this.situation = situation;
   }   
   
   @Override
   public String toString() {
      return super.toString() + "\n" + "DisasterArticle [type=" + type + ", name=" + name + ", area=" + area + ", damageAmount=" + damageAmount
            + ", situation=" + situation + "]";
   }
}