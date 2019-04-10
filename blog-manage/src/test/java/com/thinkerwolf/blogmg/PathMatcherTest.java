package com.thinkerwolf.blogmg;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class PathMatcherTest {
	@Test
	public void pathMatcher() {
		PathMatcher pathMatcher = new AntPathMatcher();
		System.out.println("pathMatched " + pathMatcher.match("\\pages\\**", "\\pages\\login\\login.html"));
	}

}
