package com.thinkerwolf.blog.front.service;

import java.util.List;

import com.thinkerwolf.blog.article.model.TopicCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.thinkerwolf.blog.article.mapper.ArticleMapper;
import com.thinkerwolf.blog.article.mapper.ArticleTopicMapper;
import com.thinkerwolf.blog.article.mapper.TopicMapper;
import com.thinkerwolf.blog.article.model.Topic;

@Service
public class TopicService {

	@Autowired
	TopicMapper topicMapper;
	@Autowired
	ArticleTopicMapper articleTopicMapper;
	@Autowired
	ArticleMapper articleMapper;
	
	@Transactional
	public void index(ModelMap map) {
		TopicCondition cond = new TopicCondition();
		cond.or().andNavEqualTo(1);
		List<Topic> topics = topicMapper.selectByCondition(cond);
		map.addAttribute("topics", topics);
	}

	public void topic(int id, ModelMap map) {
		Topic topic = topicMapper.selectByPrimaryKey(id);
		if (topic == null) {
			map.put("error", true);
			map.put("errorInfo", "无此主题");
		} else {
			index(map);
			map.put("topic", topic);
			map.put("title", "Easyblog-" + topic.getName());
		}
	}

}
