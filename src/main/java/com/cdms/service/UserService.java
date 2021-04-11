package com.cdms.service;

import java.util.List;

import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;

public interface UserService {

	public JsonResponseBody getUserInfo(String id);
	public JsonResponseBody selectUserByIdentity(User user);
	public List<User> selectAllUsers();
	public JsonResponseBody insertUser(User user);
	public JsonResponseBody insertManyUsers(List<User> userList);
	public JsonResponseBody updateUser(User user);
	public JsonResponseBody deleteUserById(Integer id);
	public JsonResponseBody deleteAllUsers();
	public JsonResponseBody changePassword(User user);
	
}
