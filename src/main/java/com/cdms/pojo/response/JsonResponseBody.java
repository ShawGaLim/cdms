package com.cdms.pojo.response;

import com.cdms.pojo.Course;
import com.cdms.pojo.Grade;
import com.cdms.pojo.Group;
import com.cdms.pojo.StudentGroup;
import com.cdms.pojo.StudentHomework;
import com.cdms.pojo.Topic;

public class JsonResponseBody {

	private Integer code;
    private String message;
    private ResponseData data;
    private Course course;
    private StudentGroup sg;
    private Group group;
    private Topic topic;
    private StudentHomework sh;
    private Grade grade;

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseData getData() {
		return data;
	}
	public void setData(ResponseData data) {
		this.data = data;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public StudentGroup getSg() {
		return sg;
	}
	public void setSg(StudentGroup sg) {
		this.sg = sg;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public StudentHomework getSh() {
		return sh;
	}
	public void setSh(StudentHomework sh) {
		this.sh = sh;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
    
}
