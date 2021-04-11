package com.cdms.service;

import java.util.List;

import com.cdms.pojo.Course;
import com.cdms.pojo.response.JsonResponseBody;

public interface CourseService {

	public JsonResponseBody selectCourseById(Integer id);
	public JsonResponseBody selectCourseByTeacherId(Integer teacherId);
	public JsonResponseBody selectAllCourses(Course course);
	public JsonResponseBody insertCourse(Course course);
	public JsonResponseBody insertManyCourses(List<Course> courseList);
	public JsonResponseBody updateCourse(Course course);
	public JsonResponseBody deleteCourseById(Integer id);
	public JsonResponseBody deleteAllCourses();
	
}
