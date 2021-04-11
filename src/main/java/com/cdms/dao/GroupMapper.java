package com.cdms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdms.pojo.Group;

public interface GroupMapper {

	public Integer countGroupWithTopic(Integer courseId);
	public Integer countGroupWithoutTopic(Integer courseId);
	public Group selectGroup(Group group);
	public Integer selectGroupId(Integer courseId);
	public List<Group> selectGroupByCourseId(@Param("courseId")Integer courseId, 
			@Param("index")Integer index, @Param("size")Integer size);
	public List<Group> selectAllGroups();
	public void insertGroup(Group group);
	public void updateGroup(Group group);
	public void deleteGroup(Group group);
	public void deleteGroupByCourseId(Integer courseId);
	public void deleteAllGroups();
	
}
