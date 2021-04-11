package com.cdms.service;

import com.cdms.pojo.StudentGroup;
import com.cdms.pojo.response.JsonResponseBody;

public interface StudentGroupService {

	public JsonResponseBody selectSG(StudentGroup sg);
	public JsonResponseBody selectGroupSGs(StudentGroup sg);
	public JsonResponseBody insertSG(StudentGroup sg);
	public JsonResponseBody updateSG(StudentGroup sg);
	public JsonResponseBody deleteSG(StudentGroup sg);
	
}
