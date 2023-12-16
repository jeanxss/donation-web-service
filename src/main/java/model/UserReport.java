package model;

import java.util.Date;

public class UserReport {
	private int reportId;
	private Date reportDate;
	private String reporterId;
	private String reportedId;
	
	public UserReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserReport(int reportId, Date reportDate, String reporterId, String reportedId) {
		super();
		this.reportId = reportId;
		this.reportDate = reportDate;
		this.reporterId = reporterId;
		this.reportedId = reportedId;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public String getReportedId() {
		return reportedId;
	}

	public void setReportedId(String reportedId) {
		this.reportedId = reportedId;
	}

	@Override
	public String toString() {
		return "UserReport [reportId=" + reportId + ", reportDate=" + reportDate + ", reporterId=" + reporterId
				+ ", reportedId=" + reportedId + "]";
	}
	
	
}
