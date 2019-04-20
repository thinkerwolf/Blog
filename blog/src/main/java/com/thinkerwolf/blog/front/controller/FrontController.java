package com.thinkerwolf.blog.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkerwolf.blog.front.service.TopicService;

@Controller
public class FrontController {

	@Autowired
	TopicService topicService;

	@RequestMapping(value = { "/index", "/" })
	public String index(ModelMap map) {
		map.addAttribute("title", "首页-Easyblog");
		topicService.index(map);
		return "index";
	}

	@RequestMapping("/topic/{id}")
	public String topic(ModelMap map, @PathVariable("id") int id) {
		// 获取相应topic
		topicService.topic(id, map);
		return "topic";
	}

}
