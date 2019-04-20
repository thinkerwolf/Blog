package com.thinkerwolf.blog.article.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkerwolf.blog.article.dto.ArticleMemberTopicsDto;
import com.thinkerwolf.blog.article.mapper.ArticleMapper;
import com.thinkerwolf.blog.article.mapper.TopicMapper;
import com.thinkerwolf.blog.article.model.Article;
import com.thinkerwolf.blog.article.model.ArticleCondition;
import com.thinkerwolf.blog.article.model.Topic;
import com.thinkerwolf.blog.article.model.TopicCondition;
import com.thinkerwolf.blog.common.json.JsonBuilder;
import com.thinkerwolf.blog.member.mapper.MemberMapper;
import com.thinkerwolf.blog.member.model.Member;

@Service
public class ArticleService {

	@Autowired
	ArticleMapper articleMapper;

	@Autowired
	TopicMapper topicMapper;

	@Autowired
	MemberMapper memberMapper;

	public Map<String, Object> getTopicArticles(int topicId, int start, int num) {
		ArticleCondition cond = new ArticleCondition();
		cond.createCriteria().addCriterion("topic_id=", String.valueOf(topicId), "topicId");
		cond.setOrderByClause("create_time desc");
		cond.setLimition(start + "," + num);
		List<Article> articles = articleMapper.selectTopicArticles(cond);
		List<ArticleMemberTopicsDto> dtos = new LinkedList<>();
		for (Article article : articles) {
			TopicCondition tcond = new TopicCondition();
			tcond.or().addCriterion("article_id = ", article.getId(), "articleId");
			List<Topic> topicList = topicMapper.getAriticleTopics(tcond);
			Member member = memberMapper.selectByPrimaryKey(article.getMemberId());
			ArticleMemberTopicsDto dto = new ArticleMemberTopicsDto();
			dto.setArticle(article);
			dto.setTopics(topicList);
			dto.setMember(member);
			dtos.add(dto);
		}
		return JsonBuilder.getSucJson(dtos);
	}

}
