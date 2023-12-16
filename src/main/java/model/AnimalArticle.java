package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class AnimalArticle extends DonationArticle{
	private String name;
	private String area;
	private String type;
	private String age;
	private String weight;
	private String gender;
	private String neutering;
	private String currentStatus;
	private String healthStatus;
	private String personality;
	
	public AnimalArticle() {}
	
	public AnimalArticle(int articleId, String title, String category, String deadline, String bankName,
			String accHolder, String accNum, String idCheck, String dueDate, String usePlan, String otherText,
			Date createDate, Date updateDate, String receiptCheck, String userId, int totalAmount, String name,
			String area, String type, String age, String weight, String gender, String neutering, String currentStatus,
			String healthStatus, String personality) {
		super(articleId, title, category, deadline, bankName, accHolder, accNum, idCheck, dueDate, usePlan, otherText,
				createDate, updateDate, receiptCheck, totalAmount, userId);
		this.name = name;
		this.area = area;
		this.type = type;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.neutering = neutering;
		this.currentStatus = currentStatus;
		this.healthStatus = healthStatus;
		this.personality = personality;
	}
	
	public AnimalArticle(int articleId, String title, String category, String deadline, String bankName,
			String accHolder, String accNum, String dueDate, String usePlan, String otherText,
			Date createDate, Date updateDate, String receiptCheck, String userId, int totalAmount, String name,
			String area, String type, String age, String weight, String gender, String neutering, String currentStatus,
			String healthStatus, String personality) {
		super(articleId, title, category, deadline, bankName, accHolder, accNum, dueDate, usePlan, otherText,
				createDate, updateDate, receiptCheck, userId, totalAmount);
		this.name = name;
		this.area = area;
		this.type = type;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.neutering = neutering;
		this.currentStatus = currentStatus;
		this.healthStatus = healthStatus;
		this.personality = personality;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNeutering() {
		return neutering;
	}

	public void setNeutering(String neutering) {
		this.neutering = neutering;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	@Override
	public String toString() {
		return super.toString()+"\n"+"AnimalArticle [name=" + name + ", area=" + area + ", type=" + type + ", age=" + age + ", weight="
				+ weight + ", gender=" + gender + ", neutering=" + neutering + ", currentStatus=" + currentStatus
				+ ", healthStatus=" + healthStatus + ", personality=" + personality + "]";
	}
}
