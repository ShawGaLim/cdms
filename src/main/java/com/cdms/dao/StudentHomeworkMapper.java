package com.cdms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdms.pojo.StudentHomework;

public interface StudentHomeworkMapper {
	
	public StudentHomework selectSH(StudentHomework sh);
	public List<StudentHomework> selectMySH(StudentHomework sh);
	public List<StudentHomework> selectSHByHomeworkId(@Param("hid")Integer hid, 
			@Param("index")Integer index, @Param("size")Integer size);
	public Integer countSHByHomeworkId(Integer hid);
	public List<StudentHomework> selectAllHSs();
	public void insertSH(StudentHomework sh);
	public void updateSH(StudentHomework sh);
	public void deleteAllSHs();
	
}
