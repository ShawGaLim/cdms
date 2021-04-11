package com.cdms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.CourseMapper;
import com.cdms.dao.StudentCourseMapper;
import com.cdms.dao.UserMapper;
import com.cdms.pojo.Course;
import com.cdms.pojo.StudentCourse;
import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.StudentCourseService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class StudentCourseServiceImpl implements StudentCourseService {

	@Autowired
	private StudentCourseMapper scm;
	@Autowired
	private UserMapper um;
	@Autowired
	private CourseMapper cm;

	@Override
	public JsonResponseBody selectSCs(StudentCourse sc) {
		Integer size = sc.getLimit();
		Integer index = (sc.getPage() - 1) * size;
		List<StudentCourse> scList = scm.selectPageSCs(index,size);
		Integer total = scm.countAllSCs();
		return JsonResponse.responseList(scList, total);
	}

	@Override
	public JsonResponseBody selectSCByCourseId(StudentCourse sc) {
		Integer size = sc.getLimit();
		Integer index = (sc.getPage() - 1) * size;
		List<StudentCourse> scList = scm.selectSCByCourseId(sc.getCourse().getId(), index, size);
		Integer total = scm.countAllSCs();
		return JsonResponse.responseList(scList, total);
	}

	@Override
	public JsonResponseBody selectSCByUserId(Integer userId) {
		return JsonResponse.responseList(scm.selectSCByUserId(userId), null);
	}

	@Override
	public JsonResponseBody insertSC(StudentCourse sc) {
		if(cm.selectCourseById(sc.getCourse().getId())==null) {
			return JsonResponse.responseFailure("课程不存在");
		} else if(um.selectUserById(sc.getStudent().getId().toString())==null) {
			return JsonResponse.responseFailure("学生不存在");
		} else {
			scm.insertSC(sc);
			return JsonResponse.responseSuccess();
		}
	}

	@Override
	public JsonResponseBody insertManySCs(List<StudentCourse> scList) {
		
		List<User> uL = um.selectAllUsers();
		List<Course> cL = cm.selectAllCourses();
		List<StudentCourse> dbscList = scm.selectAllSCs();
		
		List<StudentCourse> newList1 = scList.stream().filter((item) -> 
			(uL.contains(item.getStudent()) && cL.contains(item.getCourse()))).collect(Collectors.toList());
		if(newList1.isEmpty()) {
			return JsonResponse.responseFailure("课程或学生不存在");
		}
		
		List<StudentCourse> newList2 = newList1.stream().filter((item) -> !dbscList.contains(item)).collect(Collectors.toList());
		if(newList2.isEmpty()) {
			return JsonResponse.responseFailure("选课已导入");
		}
		
		scm.insertManySCs(newList2);
		return JsonResponse.responseSuccess();
	}
	
	@Override
	public JsonResponseBody updateSC(StudentCourse sc) {
		try {
			scm.updateReplyGrade(sc);
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("打分失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateReplyGrade(StudentCourse sc) {
		try {
			scm.updateReplyGrade(sc);
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("修改失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteSCbyUserIdorCourseId(StudentCourse sc) {
		try {
			scm.deleteSCbyUserIdorCourseId(sc);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("选课删除失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteAllSCs() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
