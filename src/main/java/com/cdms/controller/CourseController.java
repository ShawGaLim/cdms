package com.cdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.Course;
import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService cs;
	
	@PostMapping("/info")
	@ResponseBody
	public JsonResponseBody courseInfo(@RequestBody Course course) {
		return cs.selectCourseById(course.getId());
	}
	
	@PostMapping("/select")
	@ResponseBody
	public JsonResponseBody select(@RequestBody Course course) {
		return cs.selectCourseById(course.getId());
	}
	
	@PostMapping("/selectAllCourses")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody selectAllCourses(@RequestBody Course course) {
		return cs.selectAllCourses(course);
	}
	
	@PostMapping("/selectCourseByTeacherId")
	@PreAuthorize("hasRole('ROLE_teacher')")
	@ResponseBody
	public JsonResponseBody selectCourseByTeacherId(@RequestBody User user) {
		return cs.selectCourseByTeacherId(user.getId());
	}
	
	@PostMapping("/insertCourse")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody insertCourse(@RequestBody Course course) {
		return cs.insertCourse(course);
	}
	
	@PostMapping("/insertManyCourses")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody insertMany(@RequestBody List<Course> courseList) {
		return cs.insertManyCourses(courseList);
	}
	
	@PostMapping("/updateCourse")
	@ResponseBody
	public JsonResponseBody update(@RequestBody Course course) {
		return cs.updateCourse(course);
	}
	
	@PostMapping("/deleteCourse")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody delete(@RequestBody Course course) {
		return cs.deleteCourseById(course.getId());
	}
	
	@GetMapping("/deleteAll")
	@PreAuthorize("hasRole('ROLE_admin')")
	public JsonResponseBody deleteAll() {
		return null;
	}
	
}
