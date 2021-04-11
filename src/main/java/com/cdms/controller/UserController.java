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

import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.security.JwtTokenUtil;
import com.cdms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService us;
	
	@GetMapping("/info")
	public JsonResponseBody userInfo(String token) {
		String id = JwtTokenUtil.parseToken(token);
		return us.getUserInfo(id);
	}
	
	@PostMapping("/selectUserByIdentity")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody selectUserByIdentity(@RequestBody User user){
		return us.selectUserByIdentity(user);
	}
	
//	@GetMapping("/selectAllUsers")
//	@PreAuthorize("hasRole('ROLE_admin')")
//	public JsonResponseBody selectAllUsers(){
//		return JsonResponse.responseList(us.selectAllUsers());
//	}
	
	@PostMapping("/insertUser")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody insertUser(@RequestBody User user) {
		return us.insertUser(user);
	}
	
	@PostMapping("/insertManyUsers")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody insertManyUsers(@RequestBody List<User> userList) {
		return us.insertManyUsers(userList);
	}
	
	@PostMapping("/updateUser")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody updateUser(@RequestBody User user) {
		return us.updateUser(user);
	}
	
	@PostMapping("/changePassword")
	@ResponseBody
	public JsonResponseBody changePassword(@RequestBody User user) {
		return us.changePassword(user);
	}
	
	@PostMapping("/deleteUserById")
	@PreAuthorize("hasRole('ROLE_admin')")
	@ResponseBody
	public JsonResponseBody deleteUserById(@RequestBody User user) {
		return us.deleteUserById(user.getId());
	}
	
	@GetMapping("/deleteAll")
	@PreAuthorize("hasRole('ROLE_admin')")
	public void deleteAll() {
		us.deleteAllUsers();
	}
		
}
