package com.cdms.dao;

import java.util.List;

import com.cdms.pojo.Homework;

public interface HomeworkMapper {

	public Homework selectHomeworkById(Integer id);
	public List<Homework> selectHomeworkByCourseId(Integer courseId);
	public List<Homework> selectAllHomeworks();
	public void insertHomework(Homework homework);
	public void updateHomework(Homework homework);
	public void deleteHomeworkById(Integer id);
	public void deleteAllHomeworks();
	
}
