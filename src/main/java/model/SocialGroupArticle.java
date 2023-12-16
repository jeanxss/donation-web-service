package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SocialGroupArticle extends DonationArticle 
{
  //private int article_id; //부모클래스에 존재
    private int age;
    private String gender;
    private String type;
    private String area;
    private String situation; //현재 상황
    
    public SocialGroupArticle() {}

    public SocialGroupArticle(int articleId, String title, String category, String deadline, String bankName,
            String accHolder, String accNum, String idCheck, String dueDate, String usePlan, String otherText,
            Date createDate, Date updateDate, String receiptCheck, int totalAmount, String userId, int age,
            String gender, String type, String area, String situation) {
        super(articleId, title, category, deadline, bankName, accHolder, accNum, idCheck, dueDate, usePlan, otherText,
                createDate, updateDate, receiptCheck, totalAmount, userId);
        this.age = age;
        this.gender = gender;
        this.type = type;
        this.area = area;
        this.situation = situation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "SocialGroupArticle [age=" + age + ", gender=" + gender + ", type=" + type + ", area=" + area
                + ", situation=" + situation + "]";
    }
    
}