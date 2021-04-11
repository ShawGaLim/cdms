package com.cdms.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.GroupMapper;
import com.cdms.dao.StudentGroupMapper;
import com.cdms.dao.UserMapper;
import com.cdms.pojo.Group;
import com.cdms.pojo.StudentGroup;
import com.cdms.pojo.User;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.GroupService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper gm;
	@Autowired
	private StudentGroupMapper sgm;
	@Autowired
	private UserMapper um;
	
	@Override
	public JsonResponseBody selectGroup(Group group) {
		JsonResponseBody jrb = new JsonResponseBody();
		jrb.setCode(200);
		jrb.setMessage("操作成功");
		jrb.setGroup(gm.selectGroup(group));
		return jrb;
	}

	@Override
	public JsonResponseBody selectGroupByCourseId(Group group) {
		Integer size = group.getLimit();
		Integer index = (group.getPage() - 1) * size;
		Integer total = gm.countGroupWithoutTopic(group.getCourseId()) + 
				gm.countGroupWithTopic(group.getCourseId());
		List<Group> gL = gm.selectGroupByCourseId(group.getCourseId(), index, size);
		gL.sort(Comparator.comparing(Group::getId));
		return JsonResponse.responseList(gL, total);
	}

	@Override
	public JsonResponseBody selectAllGroups() {
		List<Group> gL = gm.selectAllGroups();
		gL.sort(Comparator.comparing(Group::getId));
		return null;
	}

	@Override
	public JsonResponseBody insertGroup(Group group) {
		User student = um.selectUserById(group.getCreator().getId().toString());
		if (student==null || !student.getIdentity().equals("学生")) {
			return JsonResponse.responseFailure("学生用户不存在");
		}
		Integer id = gm.selectGroupId(group.getCourseId());
		group.setId(id+1);
		StudentGroup sg = new StudentGroup();
		sg.setCourseId(group.getCourseId());
		sg.setGroupId(group.getId());
		sg.setStudent(group.getCreator());
		sg.setIdentity("组长");
		if (sgm.selectSG(sg) != null) {
			return JsonResponse.responseFailure("用户已加入小组");
		}
		try {
			gm.insertGroup(group);
			sgm.insertSG(sg);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("创建失败，可能是并发冲突，请重新尝试");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateGroup(Group group) {
		try {
			gm.updateGroup(group);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("选题或修改小组失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteGroup(Group group) {
		try {
			gm.deleteGroup(group);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("解散失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteGroupByCourseId(Integer courseId) {
		gm.deleteGroupByCourseId(courseId);
		return null;
	}

	@Override
	public JsonResponseBody deleteAllGroups() {
		gm.deleteAllGroups();
		return null;
	}

}
