package com.cdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.StudentGroup;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.StudentGroupService;

@RestController
@RequestMapping("/sg")
public class StudentGroupController {

	@Autowired
	private StudentGroupService sgs;
	
	@PostMapping("/selectSG")
	@ResponseBody
	public JsonResponseBody selectSG(@RequestBody StudentGroup sg) {
		return sgs.selectSG(sg);
	}
	
	@PostMapping("/selectGroupSGs")
	@ResponseBody
	public JsonResponseBody selectGroupSGs(@RequestBody StudentGroup sg) {
		return sgs.selectGroupSGs(sg);
	}
	
	@PostMapping("/insertSG")
	@ResponseBody
	public JsonResponseBody insertSG(@RequestBody StudentGroup sg) {
		return sgs.insertSG(sg);
	}
	
	@PostMapping("/updateSG")
	@ResponseBody
	public JsonResponseBody updateSG(@RequestBody StudentGroup sg) {
		return sgs.updateSG(sg);
	}
	
	@PostMapping("/deleteSG")
	@ResponseBody
	public JsonResponseBody deleteSG(@RequestBody StudentGroup sg) {
		return sgs.deleteSG(sg);
	}
	
}
