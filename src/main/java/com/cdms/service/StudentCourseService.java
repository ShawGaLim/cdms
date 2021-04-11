package com.cdms.service;

import java.util.List;

import com.cdms.pojo.StudentCourse;
import com.cdms.pojo.response.JsonResponseBody;

public interface StudentCourseService {
	
	public JsonResponseBody selectSCs(StudentCourse sc);
	public JsonResponseBody selectSCByCourseId(StudentCourse sc);
	public JsonResponseBody selectSCByUserId(Integer userId);
	public JsonResponseBody insertSC(StudentCourse sc);
	public JsonResponseBody insertManySCs(List<StudentCourse> scList);
	public JsonResponseBody updateSC(StudentCourse sc);
	public JsonResponseBody updateReplyGrade(StudentCourse sc);
	public JsonResponseBody deleteSCbyUserIdorCourseId(StudentCourse sc);
	public JsonResponseBody deleteAllSCs();
	
}
