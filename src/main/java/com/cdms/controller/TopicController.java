package com.cdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.Topic;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private TopicService ts;
	
	@PostMapping("/selectTopicById")
	@ResponseBody
	public JsonResponseBody selectTopicById(@RequestBody Topic topic) {
		return ts.selectTopicById(topic.getId());
	}
	
	@PostMapping("/selectTopicByCourseId")
	@ResponseBody
	public JsonResponseBody selectTopicByCourseId(@RequestBody Topic topic) {
		return ts.selectTopicByCourseId(topic);
	}
	
	@GetMapping("/selectAll")
	public JsonResponseBody selectAll() {
		return ts.selectAllTopics();
	}
	
	@PostMapping("/insertTopic")
	@ResponseBody
	public JsonResponseBody insert(@RequestBody Topic topic) {
		return ts.insertTopic(topic);
	}
	
	@PostMapping("/insertManyTopics")
	@ResponseBody
	public JsonResponseBody insertMany(@RequestBody List<Topic> topicList) {
		return ts.insertManyTopics(topicList);
	}
	
	@PostMapping("/updateTopic")
	@ResponseBody
	public JsonResponseBody update(@RequestBody Topic topic) {
		return ts.updateTopic(topic);
	}
	
	@PostMapping("/deleteTopic")
	@ResponseBody
	public JsonResponseBody delete(@RequestBody Topic topic) {
		return ts.deleteTopicById(topic.getId());
	}
	
	@PostMapping("/deleteByCourseId")
	@ResponseBody
	public JsonResponseBody deleteByCourseId(@RequestBody Topic topic) {
		return ts.deleteTopicByCourseId(topic.getCourseId());
	}
	
	@PostMapping("/deleteAll")
	@ResponseBody
	public JsonResponseBody deleteAll() {
		return ts.deleteAllTopics();
	}
	
}
