package com.cdms.service;

import java.util.List;

import com.cdms.pojo.Topic;
import com.cdms.pojo.response.JsonResponseBody;

public interface TopicService {

	public JsonResponseBody selectTopicById(Integer id);
	public JsonResponseBody selectTopicByCourseId(Topic topic);
	public JsonResponseBody selectAllTopics();
	public JsonResponseBody insertTopic(Topic topic);
	public JsonResponseBody insertManyTopics(List<Topic> topicList);
	public JsonResponseBody updateTopic(Topic topic);
	public JsonResponseBody deleteTopicById(Integer id);
	public JsonResponseBody deleteTopicByCourseId(Integer courseId);
	public JsonResponseBody deleteAllTopics();
	
}
