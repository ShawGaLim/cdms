package com.cdms.service;

import com.cdms.pojo.Group;
import com.cdms.pojo.response.JsonResponseBody;

public interface GroupService {

	public JsonResponseBody selectGroup(Group group);
	public JsonResponseBody selectGroupByCourseId(Group group);
	public JsonResponseBody selectAllGroups();
	public JsonResponseBody insertGroup(Group group);
	public JsonResponseBody updateGroup(Group group);
	public JsonResponseBody deleteGroup(Group group);
	public JsonResponseBody deleteGroupByCourseId(Integer courseId);
	public JsonResponseBody deleteAllGroups();
	
}
