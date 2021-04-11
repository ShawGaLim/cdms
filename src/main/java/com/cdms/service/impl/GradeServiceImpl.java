package com.cdms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.GradeMapper;
import com.cdms.dao.StudentCourseMapper;
import com.cdms.pojo.Grade;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.GradeService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeMapper gm;
	@Autowired
	private StudentCourseMapper scm;
	
	@Override
	public JsonResponseBody selectMyAllGrade(Grade grade) {
		return JsonResponse.responseList(gm.selectMyAllGrade(grade), null);
	}

	@Override
	public JsonResponseBody selectMyGrade(Grade grade) {
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setGrade(gm.selectMyGrade(grade));
		responseBody.setCode(200);
		responseBody.setMessage("成功获取课程信息");
		return responseBody;
	}

	@Override
	public JsonResponseBody selectGrade(Grade grade) {
		return JsonResponse.responseList(gm.selectGrade(grade), null);
	}

	@Override
	public JsonResponseBody insertGrade(Grade grade) {
		Grade oldGrade = gm.selectMyGrade(grade);
		try {
			gm.insertGrade(grade);
			if(oldGrade != null) {
				grade.setGrade(grade.getGrade() - oldGrade.getGrade());
			}
			scm.updateSC(grade.getStudent().getId(), grade.getCourseId(), null, grade.getGrade());
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("打分失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateGrade(Grade grade) {
		try {
			gm.updateGrade(grade);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("打分失败");
		}
		return JsonResponse.responseSuccess();
	}

}
