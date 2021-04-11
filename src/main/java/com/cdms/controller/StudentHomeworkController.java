package com.cdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.StudentHomework;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.StudentHomeworkService;

@RestController
@RequestMapping("/sh")
public class StudentHomeworkController {

	@Autowired
	private StudentHomeworkService shs;
	
	@PostMapping("/select")
	@ResponseBody
	public JsonResponseBody select(@RequestBody StudentHomework sh) {
		return shs.selectSHByUserIdandHomeworkId(sh);
	}
	
	@PostMapping("/selectSH")
	@ResponseBody
	public JsonResponseBody selectSH(@RequestBody StudentHomework sh) {
		return shs.selectSH(sh);
	}
	
	@PostMapping("/selectMySH")
	@ResponseBody
	public JsonResponseBody selectMySH(@RequestBody StudentHomework sh) {
		return shs.selectMySH(sh);
	}
	
	@PostMapping("/selectByHomeworkId")
	@ResponseBody
	public JsonResponseBody selectByHomeworkId(@RequestBody StudentHomework sh) {
		return shs.selectSHByHomeworkId(sh);
	}
	
	@GetMapping("/selectAll")
	public JsonResponseBody selectAll() {
		return shs.selectAllHSs();
	}
	
	@PostMapping("/insertSH")
	@ResponseBody
	public JsonResponseBody insert(@RequestBody StudentHomework sh) {
		return shs.insertSH(sh);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public JsonResponseBody update(@RequestBody StudentHomework sh) {
		return shs.updateSH(sh);
	}
	
	@GetMapping("/delete")
	public JsonResponseBody delete() {
		return shs.deleteAllSHs();
	}
	
}
