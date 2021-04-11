package com.cdms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.HomeworkMapper;
import com.cdms.dao.StudentHomeworkMapper;
import com.cdms.pojo.Homework;
import com.cdms.pojo.StudentHomework;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.StudentHomeworkService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class StudentHomeworkServiceImpl implements StudentHomeworkService {

	@Autowired
	private StudentHomeworkMapper shm;
	@Autowired
	private HomeworkMapper hm;

	@Override
	public JsonResponseBody selectSH(StudentHomework sh) {
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setSh(shm.selectSH(sh));
		responseBody.setCode(200);
		responseBody.setMessage("成功获取课程信息");
		return responseBody;
	}
	
	@Override
	public JsonResponseBody selectMySH(StudentHomework sh) {
		return JsonResponse.responseList(shm.selectMySH(sh), null);
	}

	@Override
	public JsonResponseBody selectSHByUserIdandHomeworkId(StudentHomework sh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponseBody selectSHByHomeworkId(StudentHomework sh) {
		Integer size = sh.getLimit();
		Integer index = (sh.getPage() - 1) * size;
		Integer total = shm.countSHByHomeworkId(sh.getHomework().getId());
		return JsonResponse.responseList(shm.selectSHByHomeworkId(sh.getHomework().getId(), index, size), total);
	}

	@Override
	public JsonResponseBody selectAllHSs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponseBody insertSH(StudentHomework sh) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Homework h = hm.selectHomeworkById(sh.getHomework().getId());
		try {
			Date deadline = format.parse(h.getDeadline());
			Date now = new Date();
			if(now.after(deadline)) {
				sh.setStatus("延时提交");
			} else {
				sh.setStatus("按时提交");
			}
			shm.insertSH(sh);
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("提交失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateSH(StudentHomework sh) {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public JsonResponseBody deleteAllSHs() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
