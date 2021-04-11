package com.cdms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.UserMapper;
import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.pojo.response.ResponseData;
import com.cdms.service.UserService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper um;
	
	@Override
	public JsonResponseBody getUserInfo(String id) {
		
		User user = um.selectUserById(id);
		JsonResponseBody responseBody = new JsonResponseBody();
		ResponseData data = new ResponseData();
		String[] role = { user.getIdentity() };
		
		data.setId(id);
		data.setName(user.getUsername());
		data.setRoles(role);
		data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
		
		responseBody.setData(data);
		responseBody.setCode(200);
		responseBody.setMessage("操作成功");
		
		return responseBody;
	}

	@Override
	public JsonResponseBody selectUserByIdentity(User user) {
		Integer size = user.getLimit();
		Integer index = (user.getPage() - 1) * size;
		Integer total = um.countUserByIdentity(user.getIdentity());
		List<User> ul = um.selectUserByIdentity(user.getIdentity(), index, size);
		return JsonResponse.responseList(ul, total);
	}

	@Override
	public List<User> selectAllUsers() {
		return um.selectAllUsers();
	}

	@Override
	public JsonResponseBody insertUser(User user) {		
		if(um.selectUserById(user.getId().toString())!=null) {
			return JsonResponse.responseFailure("用户ID已存在");
		} else {
			um.insertUser(user);
			return JsonResponse.responseSuccess();
		}
	}

	@Override
	public JsonResponseBody insertManyUsers(List<User> userList) {
		List<User> dbList = um.selectAllUsers();
		List<User> newList = userList.stream().filter((item) -> !dbList.contains(item)).collect(Collectors.toList());
		if(newList.isEmpty()) {
			return JsonResponse.responseFailure("所有用户都已存在");
		} else {
			um.insertManyUsers(newList);
			return JsonResponse.responseSuccess();
		}
	}

	@Override
	public JsonResponseBody updateUser(User user) {
		try {
			um.updateUser(user);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("更新失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteUserById(Integer id) {
		try {
			um.deleteUserById(id);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("删除失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteAllUsers() {
		um.deleteAllUsers();
		return null;
	}

	@Override
	public JsonResponseBody changePassword(User user) {
		User u = um.selectUserById(user.getId().toString());
		if (!u.getPassword().equals(user.getPassword())) {
			return JsonResponse.responseFailure("旧密码不正确");
		}
		try {
			um.updateUser(user);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("修改失败");
		}
		return JsonResponse.responseSuccess();
	}
	
	

}
