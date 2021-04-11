package com.cdms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdms.pojo.StudentCourse;

public interface StudentCourseMapper {

	public StudentCourse selectSC(StudentCourse sc);
	public List<StudentCourse> selectAllSCs();
	public List<StudentCourse> selectPageSCs(@Param("index")Integer index, @Param("size")Integer size);
	public Integer countAllSCs();
	public List<StudentCourse> selectSCByCourseId(@Param("courseId")Integer courseId, 
			@Param("index")Integer index, @Param("size")Integer size);
	public List<StudentCourse> selectSCByUserId(Integer userId);
	public void insertSC(StudentCourse sc);
	public void insertManySCs(List<StudentCourse> scList);
	public void updateSC(@Param("sid")Integer sid, @Param("cid")Integer cid, 
			@Param("replyGrade")Integer replyGrade, @Param("totalGrade")Integer totalGrade);
	public void updateReplyGrade(StudentCourse sc);
	public void deleteSCbyUserIdorCourseId(StudentCourse sc);
	public void deleteAllSCs();
	
}
