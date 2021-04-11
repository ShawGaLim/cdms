package com.cdms.pojo;

public class Course {

	private Integer id;
	private String name;
	private String status;
	private String createTime;
	private User teacher;
	private Integer page;
	private Integer limit;
	private Integer maxGroupMember;
	private Integer maxReplyGrade;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public User getTeacher() {
		return teacher;
	}
	
	public void setTeacher(User teacher) {
		this.teacher = teacher;
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
	
	public Integer getMaxGroupMember() {
		return maxGroupMember;
	}

	public void setMaxGroupMember(Integer maxGroupMember) {
		this.maxGroupMember = maxGroupMember;
	}

	public Integer getMaxReplyGrade() {
		return maxReplyGrade;
	}

	public void setMaxReplyGrade(Integer maxReplyGrade) {
		this.maxReplyGrade = maxReplyGrade;
	}

	@Override
	public boolean equals(Object obj) {
		Course c = (Course)obj;
		if(this.getId().equals(c.getId())) {
			return true;
		} else {
			return false;
		}
	}
	
}
	
