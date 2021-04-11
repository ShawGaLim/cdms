package com.cdms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdms.pojo.User;

public interface UserMapper {

	public User selectUserById(String id);
	public List<User> selectUserByIdentity(@Param("identity")String identity, @Param("index")Integer index, @Param("size")Integer size);
	public Integer countUserByIdentity(String identity);
	public List<User> selectAllUsers();
	public void insertUser(User user);
	public void insertManyUsers(List<User> userList);
	public void updateUser(User user);
	public void deleteUserById(Integer id);
	public void deleteAllUsers();
	
}
