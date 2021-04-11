package com.cdms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.HomeworkMapper;
import com.cdms.pojo.Homework;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.HomeworkService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class HomeworkServiceImpl implements HomeworkService {

	@Autowired
	private HomeworkMapper hm;

	@Override
	public JsonResponseBody selectHomeworkByCourseId(Integer courseId) {
		return JsonResponse.responseList(hm.selectHomeworkByCourseId(courseId), null);
	}

	@Override
	public JsonResponseBody selectAllHomeworks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponseBody insertHomework(Homework homework) {
		try {
			hm.insertHomework(homework);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("发布作业失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateHomework(Homework homework) {
		try {
			hm.updateHomework(homework);
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("作业修改失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteHomeworkById(Integer id) {
		try {
			hm.deleteHomeworkById(id);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("删除失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteAllHomeworks() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
