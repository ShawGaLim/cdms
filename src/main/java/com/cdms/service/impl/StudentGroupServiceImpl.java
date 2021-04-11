package com.cdms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.CourseMapper;
import com.cdms.dao.GroupMapper;
import com.cdms.dao.StudentGroupMapper;
import com.cdms.pojo.Group;
import com.cdms.pojo.StudentGroup;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.StudentGroupService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class StudentGroupServiceImpl implements StudentGroupService {

	@Autowired
	private StudentGroupMapper sgm;
	@Autowired
	private GroupMapper gm;
	@Autowired
	private CourseMapper cm;

	@Override
	public JsonResponseBody selectSG(StudentGroup sg) {
		JsonResponseBody jrb = new JsonResponseBody();
		jrb.setCode(200);
		jrb.setMessage("操作成功");
		jrb.setSg(sgm.selectSG(sg));
		return jrb;
	}

	@Override
	public JsonResponseBody selectGroupSGs(StudentGroup sg) {
		return JsonResponse.responseList(sgm.selectGroupSGs(sg), null);
	}

	@Override
	public JsonResponseBody insertSG(StudentGroup sg) {
		if (sgm.selectGroupSGs(sg).size() >= cm.selectCourseById(sg.getCourseId()).getMaxGroupMember()) {
			return JsonResponse.responseFailure("已满人");
		}
		try {
			sgm.insertSG(sg);
			
			Group g = new Group();
			g.setCourseId(sg.getCourseId());
			g.setId(sg.getGroupId());
			g.setMember(1);
			gm.updateGroup(g);
			
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("加入失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateSG(StudentGroup sg) {		
		StudentGroup oldLeader = sgm.selectLeader(sg);
		oldLeader.setIdentity("组员");
		try {
			sgm.updateSG(sg);
			sgm.updateSG(oldLeader);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("任命失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteSG(StudentGroup sg) {
		try {
			sgm.deleteSG(sg);
			
			Group g = new Group();
			g.setCourseId(sg.getCourseId());
			g.setId(sg.getGroupId());
			g.setMember(-1);
			gm.updateGroup(g);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("请离失败");
		}
		return JsonResponse.responseSuccess();
	}

}
