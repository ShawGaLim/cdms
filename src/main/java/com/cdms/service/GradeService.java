package com.cdms.service;

import com.cdms.pojo.Grade;
import com.cdms.pojo.response.JsonResponseBody;

public interface GradeService {

	public JsonResponseBody selectMyGrade(Grade grade);
	public JsonResponseBody selectMyAllGrade(Grade grade);
	public JsonResponseBody selectGrade(Grade grade);
	public JsonResponseBody insertGrade(Grade grade);
	public JsonResponseBody updateGrade(Grade grade);
	
}
