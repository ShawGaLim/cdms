package com.cdms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdms.pojo.Topic;

public interface TopicMapper {

	public Topic selectTopicById(Integer id);
	public List<Topic> selectTopicByCourseId(@Param("courseId")Integer courseId, 
			@Param("index")Integer index, @Param("size")Integer size);
	public Integer countTopicByCourseId(Integer courseId);
	public List<Topic> selectAllTopics();
	public void insertTopic(Topic topic);
	public void insertManyTopics(List<Topic> topicList);
	public void updateTopic(Topic topic);
	public void deleteTopicById(Integer id);
	public void deleteTopicByCourseId(Integer courseId);
	public void deleteAllTopics();
	
}
