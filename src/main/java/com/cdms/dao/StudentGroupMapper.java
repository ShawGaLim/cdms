package com.cdms.dao;

import java.util.List;

import com.cdms.pojo.StudentGroup;

public interface StudentGroupMapper {

	public StudentGroup selectSG(StudentGroup sg);
	public List<StudentGroup> selectGroupSGs(StudentGroup sg);
	public StudentGroup selectLeader(StudentGroup sg);
	public void insertSG(StudentGroup sg);
	public void updateSG(StudentGroup sg);
	public void deleteSG(StudentGroup sg);
	
}
