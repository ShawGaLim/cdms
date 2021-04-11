package com.cdms.dao;

import java.util.List;

import com.cdms.pojo.Grade;

public interface GradeMapper {

	public Grade selectMyGrade(Grade grade);
	public List<Grade> selectMyAllGrade(Grade grade);
	public List<Grade> selectGrade(Grade grade);
	public void insertGrade(Grade grade);
	public void updateGrade(Grade grade);
	
}
