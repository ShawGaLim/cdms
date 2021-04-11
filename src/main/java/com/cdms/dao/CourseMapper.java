package com.cdms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdms.pojo.Course;

public interface CourseMapper {

	public Course selectCourseById(Integer id);
	public List<Course> selectCourseByTeacherId(Integer teacherId);
	public List<Course> selectPageCourses(@Param("index")Integer index, @Param("size")Integer size);
	public List<Course> selectAllCourses();
	public Integer countAllCourses();
	public void insertCourse(Course course);
	public void insertManyCourses(List<Course> courseList);
	public void updateCourse(Course course);
	public void deleteCourseById(Integer id);
	public void deleteAllCourses();
	
}
