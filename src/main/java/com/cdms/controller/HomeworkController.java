package com.cdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.Homework;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.HomeworkService;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

	@Autowired
	private HomeworkService hs;
	
	@PostMapping("/selectHomeworkByCourseId")
	@ResponseBody
	public JsonResponseBody selectHomeworkByCourseId(@RequestBody Homework homework) {
		return hs.selectHomeworkByCourseId(homework.getCourseId());
	}
	
	@GetMapping("/selectAll")
	@ResponseBody
	public JsonResponseBody selectAll() {
		return hs.selectAllHomeworks();
	}
	
	
	@PostMapping("/insertHomework")
	@ResponseBody
	public JsonResponseBody insertHomework(@RequestBody Homework homework) {
		return hs.insertHomework(homework);
	}
		
	@PostMapping("/updateHomework")
	@ResponseBody
	public JsonResponseBody update(@RequestBody Homework homework) {
		return hs.updateHomework(homework);
	}
	
	@PostMapping("/deleteHomework")
	@ResponseBody
	public JsonResponseBody delete(@RequestBody Homework homework) {
		return hs.deleteHomeworkById(homework.getId());
	}
	
	@GetMapping("/deleteAll")
	public JsonResponseBody deleteAll() {
		return hs.deleteAllHomeworks();
	}
	
}
