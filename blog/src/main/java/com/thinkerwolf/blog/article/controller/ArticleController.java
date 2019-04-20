package com.thinkerwolf.blog.article.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkerwolf.blog.article.service.ArticleService;

@Controller
@RequestMapping("/api/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@RequestMapping("/topicats")
	@ResponseBody
	public Map<String, Object> topicArticles(@RequestParam("topicId") int topicId, @RequestParam("start") int start,
			@RequestParam("num") int num) {
		return articleService.getTopicArticles(topicId, start, num);
	}

}
