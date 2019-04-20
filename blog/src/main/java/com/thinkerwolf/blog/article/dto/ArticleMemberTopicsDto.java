package com.thinkerwolf.blog.article.dto;

import java.util.List;

import com.thinkerwolf.blog.article.model.Article;
import com.thinkerwolf.blog.article.model.Topic;
import com.thinkerwolf.blog.member.model.Member;

public class ArticleMemberTopicsDto {

	private Article article;
	private Member member;
	private List<Topic> topics;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

}
