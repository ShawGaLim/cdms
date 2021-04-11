package com.cdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.StudentCourse;
import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.StudentCourseService;

@RestController
@RequestMapping("/sc")
public class StudentCourseController {

	@Autowired
	private StudentCourseService scs;
	
	@PostMapping("/selectAllSCs")
	@ResponseBody
	public JsonResponseBody selectAllSCs(@RequestBody StudentCourse sc) {
		return scs.selectSCs(sc);
	}
	
	@PostMapping("/selectSCByCourseId")
	@PreAuthorize("hasRole('ROLE_teacher')")
	@ResponseBody
	public JsonResponseBody selectSCByCourseId(@RequestBody StudentCourse sc) {
		return scs.selectSCByCourseId(sc);
	}
	
	@PostMapping("/selectSCByUserId")
	@PreAuthorize("hasRole('ROLE_teacher') or hasRole('ROLE_student')" )
	@ResponseBody
	public JsonResponseBody selectSCByUserId(@RequestBody User user) {
		return scs.selectSCByUserId(user.getId());
	}
	
	@PostMapping("/insertSC")
	@ResponseBody
	public JsonResponseBody insert(@RequestBody StudentCourse sc) {
		return scs.insertSC(sc);
	}
	
	@PostMapping("/insertManySCs")
	@ResponseBody
	public JsonResponseBody insertMany(@RequestBody List<StudentCourse> scList) {
		return scs.insertManySCs(scList);
	}
	
	@PostMapping("/updateReplyGrade")
	@ResponseBody
	public JsonResponseBody updateReplyGrade(@RequestBody StudentCourse sc) {
		return scs.updateReplyGrade(sc);
	}
	
	@PostMapping("/deleteSC")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody delete(@RequestBody StudentCourse sc) {
		return scs.deleteSCbyUserIdorCourseId(sc);
	}
	
	@GetMapping("/deleteAll")
	public JsonResponseBody delte() {
		return scs.deleteAllSCs();
	}
}
