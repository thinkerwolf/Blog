package com.thinkerwolf.blog.article.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkerwolf.blog.common.json.JsonBuilder;

@Controller
@RequestMapping("/api/comment")
public class CommentController {
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> test() {
		return JsonBuilder.getSucJson();
	}
	
}
