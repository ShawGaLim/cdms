package com.cdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.Grade;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.GradeService;

@RestController
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	private GradeService gs;
	
	@PostMapping("/selectMyGrade")
	@ResponseBody
	public JsonResponseBody selectMyGrade(@RequestBody Grade grade) {
		return gs.selectMyGrade(grade);
	}
	
	@PostMapping("/selectMyAllGrade")
	@ResponseBody
	public JsonResponseBody selectMyAllGrade(@RequestBody Grade grade) {
		return gs.selectMyAllGrade(grade);
	}
	
	@PostMapping("/selectGrade")
	@ResponseBody
	public JsonResponseBody selectGrade(@RequestBody Grade grade) {
		return gs.selectGrade(grade);
	}
	
	@PostMapping("/insertGrade")
	@ResponseBody
	public JsonResponseBody insertGrade(@RequestBody Grade grade) {
		return gs.insertGrade(grade);
	}
	
	@PostMapping("/updateGrade")
	@ResponseBody
	public JsonResponseBody updateGrade(@RequestBody Grade grade) {
		return gs.updateGrade(grade);
	}

}
