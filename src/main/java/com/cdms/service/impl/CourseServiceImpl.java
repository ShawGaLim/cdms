package com.cdms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.CourseMapper;
import com.cdms.dao.UserMapper;
import com.cdms.pojo.Course;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.CourseService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper cm;
	@Autowired
	private UserMapper um;
	
	@Override
	public JsonResponseBody selectCourseById(Integer id) {
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCourse(cm.selectCourseById(id));
		responseBody.setCode(200);
		responseBody.setMessage("成功获取课程信息");
		return responseBody;
	}

	@Override
	public JsonResponseBody selectAllCourses(Course course) {
		Integer size = course.getLimit();
		Integer index = (course.getPage() - 1) * size;
		Integer total = cm.countAllCourses();
		List<Course> cl = cm.selectPageCourses(index, size);
		return JsonResponse.responseList(cl, total);
	}

	@Override
	public JsonResponseBody selectCourseByTeacherId(Integer teacherId) {
		return JsonResponse.responseList(cm.selectCourseByTeacherId(teacherId), null);
	}

	@Override
	public JsonResponseBody insertCourse(Course course) {
		if(cm.selectCourseById(course.getId())!=null) {
			return JsonResponse.responseFailure("课程ID已存在");
		} else if(um.selectUserById(course.getTeacher().getId().toString())==null) {
			return JsonResponse.responseFailure("教师ID不存在");
		} else {
			cm.insertCourse(course);
			return JsonResponse.responseSuccess();
		}
	}

	@Override
	public JsonResponseBody insertManyCourses(List<Course> courseList) {
		List<Course> dbList = cm.selectAllCourses();
		List<Course> newList = courseList.stream().filter((item) -> !dbList.contains(item)).collect(Collectors.toList());
		if(newList.isEmpty()) {
			return JsonResponse.responseFailure("所有课程都已存在");
		} else {
			cm.insertManyCourses(newList);
			return JsonResponse.responseSuccess();
		}
	}

	@Override
	public JsonResponseBody updateCourse(Course course) {
		try {
			cm.updateCourse(course);
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("更新失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteCourseById(Integer id) {
		try {
			cm.deleteCourseById(id);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("删除失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteAllCourses() {
		cm.deleteAllCourses();
		return null;
	}

}
