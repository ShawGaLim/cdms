package com.cdms.pojo;

public class StudentGroup {

	private Integer CourseId;
	private Integer groupId;
	private User student;
	private String identity;
	
	public Integer getCourseId() {
		return CourseId;
	}
	
	public void setCourseId(Integer courseId) {
		CourseId = courseId;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public User getStudent() {
		return student;
	}
	
	public void setStudent(User student) {
		this.student = student;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}

}
