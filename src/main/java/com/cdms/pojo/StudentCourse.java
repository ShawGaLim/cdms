package com.cdms.pojo;

public class StudentCourse {

	private User student;
	private Course course;
	private Integer replyGrade;
	private Integer totalGrade;
	private String chooseTime;
	private Integer page;
	private Integer limit;
	
	public User getStudent() {
		return student;
	}
	
	public void setStudent(User student) {
		this.student = student;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Integer getReplyGrade() {
		return replyGrade;
	}

	public void setReplyGrade(Integer replyGrade) {
		this.replyGrade = replyGrade;
	}

	public Integer getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(Integer totalGrade) {
		this.totalGrade = totalGrade;
	}

	public String getChooseTime() {
		return chooseTime;
	}

	public void setChooseTime(String chooseTime) {
		this.chooseTime = chooseTime;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	@Override
	public boolean equals(Object obj) {
		StudentCourse sc = (StudentCourse)obj;
		if(this.getStudent().getId().equals(sc.getStudent().getId()) && 
				this.getCourse().getId().equals(sc.getCourse().getId())) {
			return true;
		} else {
			return false;
		}
	}
	
}
