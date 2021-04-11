package com.cdms.service;

import com.cdms.pojo.Homework;
import com.cdms.pojo.response.JsonResponseBody;

public interface HomeworkService {

	public JsonResponseBody selectHomeworkByCourseId(Integer courseId);
	public JsonResponseBody selectAllHomeworks();
	public JsonResponseBody insertHomework(Homework homework);
	public JsonResponseBody updateHomework(Homework homework);
	public JsonResponseBody deleteHomeworkById(Integer id);
	public JsonResponseBody deleteAllHomeworks();
	
}
