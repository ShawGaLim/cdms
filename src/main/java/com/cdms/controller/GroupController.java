package com.cdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.Group;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService gs;
	
	@PostMapping("/selectGroup")
	@ResponseBody
	public JsonResponseBody selectGroup(@RequestBody Group group){
		return gs.selectGroup(group);
	}
	
	@PostMapping("/selectGroupByCourseId")
	@ResponseBody
	public JsonResponseBody selectGroupByCourseId(@RequestBody Group group){
		return gs.selectGroupByCourseId(group);
	}
	
	@GetMapping("/selectAll")
	public JsonResponseBody selectAllGroups() {
		return null;
	}
	
	@PostMapping("/insertGroup")
	@ResponseBody
	public JsonResponseBody insertGroup(@RequestBody Group group) {
		return gs.insertGroup(group);
	}
	
	@PostMapping("/updateGroup")
	@ResponseBody
	public JsonResponseBody updateGroup(@RequestBody Group group) {
		return gs.updateGroup(group);
	}
	
	
	@PostMapping("/deleteGroup")
	@ResponseBody
	public JsonResponseBody deleteGroup(@RequestBody Group group) {
		return gs.deleteGroup(group);
	}
	
	@PostMapping("/deleteByCourseId")
	@ResponseBody
	public JsonResponseBody deleteGroupByCourseId(@RequestBody Group group) {
		return null;
	}
	
	@GetMapping("/deleteAll")
	public JsonResponseBody deleteAllGroups() {
		return null;
	}
	
}
