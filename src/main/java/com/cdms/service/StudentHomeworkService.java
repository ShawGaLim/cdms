package com.cdms.service;

import com.cdms.pojo.StudentHomework;
import com.cdms.pojo.response.JsonResponseBody;

public interface StudentHomeworkService {

	public JsonResponseBody selectMySH(StudentHomework sh);
	public JsonResponseBody selectSH(StudentHomework sh);
	public JsonResponseBody selectSHByUserIdandHomeworkId(StudentHomework sh);
	public JsonResponseBody selectSHByHomeworkId(StudentHomework sh);
	public JsonResponseBody selectAllHSs();
	public JsonResponseBody insertSH(StudentHomework sh);
	public JsonResponseBody updateSH(StudentHomework sh);
	public JsonResponseBody deleteAllSHs();
	
}
