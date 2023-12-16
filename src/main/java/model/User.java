package model;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
public class User {

	private String userId;
	private String name;
	private String pwd;
	private String phoneNum;
	private String birthday;
	private String email;
	private String address;
	private int rewardAmount;
	private int reportNum; // 신고당한 횟수

	public User() { }		// 기본 생성자
	
	public User(String userId, String name, String password, String phone, String birthday, String email, String addr,
			int rewards, int report_num) {
		this.userId = userId;
		this.name = name;
		this.pwd = password;
		this.phoneNum = phone;
		this.birthday = birthday;
		this.email = email;
		this.address = addr;
		this.rewardAmount = rewards;
		this.reportNum = report_num;
	}

	//Register
	public User(String userId ,String name, String password, String phone, String birthday, String email, String addr) {
		this.userId = userId;
		this.name = name;
		this.pwd = password;
		this.phoneNum = phone;
		this.birthday = birthday;
		this.email = email;
		this.address = addr;
	}
	
	// Update
	public User(String userId , String password, String phone, String email, String addr) {
		this.userId = userId;
		this.pwd = password;
		this.phoneNum = phone;
		this.email = email;
		this.address = addr;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return pwd;
	}

	public void setPassword(String password) {
		this.pwd = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phone) {
		this.phoneNum = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String addr) {
		this.address = addr;
	}

	public int getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(int rewards) {
		this.rewardAmount = rewards;
	}

	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int report_num) {
		this.reportNum = report_num;
	}

	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.pwd.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + pwd + ", phone=" + phoneNum
				+ ", birthday=" + birthday + ", email=" + email + ", addr=" + address + "]";
	}
}
