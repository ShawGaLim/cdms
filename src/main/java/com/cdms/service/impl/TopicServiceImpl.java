package com.cdms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cdms.dao.TopicMapper;
import com.cdms.pojo.Topic;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.TopicService;
import com.cdms.utils.JsonResponse;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicMapper tm;

	@Override
	public JsonResponseBody selectTopicById(Integer id) {
		JsonResponseBody jrb = new JsonResponseBody();
		jrb.setCode(200);
		jrb.setMessage("操作成功");
		jrb.setTopic(tm.selectTopicById(id));
		return jrb;
	}

	@Override
	public JsonResponseBody selectTopicByCourseId(Topic topic) {
		Integer size = topic.getLimit();
		Integer index = (topic.getPage() - 1) * size;
		Integer total = tm.countTopicByCourseId(topic.getCourseId());
		return JsonResponse.responseList(tm.selectTopicByCourseId(topic.getCourseId(), index, size), total);
	}

	@Override
	public JsonResponseBody selectAllTopics() {
		return null;
	}

	@Override
	public JsonResponseBody insertTopic(Topic topic) {
		try {
			tm.insertTopic(topic);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("增加题目失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody insertManyTopics(List<Topic> topicList) {
		try {
			tm.insertManyTopics(topicList);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("增加题目失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody updateTopic(Topic topic) {
		try {
			tm.updateTopic(topic);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("题目更新失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteTopicById(Integer id) {
		try {
			tm.deleteTopicById(id);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("题目删除失败");
		}
		return JsonResponse.responseSuccess();
	}

	@Override
	public JsonResponseBody deleteTopicByCourseId(Integer courseId) {
		return null;
	}

	@Override
	public JsonResponseBody deleteAllTopics() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
