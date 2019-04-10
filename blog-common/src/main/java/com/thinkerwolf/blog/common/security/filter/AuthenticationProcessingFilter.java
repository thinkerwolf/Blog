package com.thinkerwolf.blog.common.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.thinkerwolf.blog.common.log.InternalLoggerFactory;
import com.thinkerwolf.blog.common.log.Logger;
import com.thinkerwolf.blog.common.security.model.SysMsg;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.event.authentication.InteractiveAuthenticationSuccessEvent;

/**
 * 授权处理过滤器
 * 
 * @author Bruce Wu
 * 
 */
public class AuthenticationProcessingFilter extends org.acegisecurity.ui.webapp.AuthenticationProcessingFilter {

	private static final Logger logger = InternalLoggerFactory.getLogger(AuthenticationProcessingFilter.class);

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		super.onSuccessfulAuthentication(request, response, authResult);
		request.getSession().setAttribute("j_username", obtainUsername(request));
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		super.onUnsuccessfulAuthentication(request, response, failed);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Authentication Successful authReult " + authResult);
		}
		SecurityContextHolder.getContext().setAuthentication(authResult);

		if (logger.isDebugEnabled()) {
			logger.debug("Updated SecurityContextHolder to contain the following Authentication: '" + authResult + "'");
		}

		String targetUrl = isAlwaysUseDefaultTargetUrl() ? null : obtainFullRequestUrl(request);

		if (targetUrl == null) {
			targetUrl = getDefaultTargetUrl();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Redirecting to target URL from HTTP Session (or default): " + targetUrl);
		}

		onSuccessfulAuthentication(request, response, authResult);

		getRememberMeServices().loginSuccess(request, response, authResult);

		if (this.eventPublisher != null) {
			eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}
		Map<String, Object> uiObject = new HashMap<String, Object>();
		SysMsg sysMsg = new SysMsg();
		sysMsg.setResult("success");
		sysMsg.setMessage("登陆成功");
		sysMsg.setTargetUrl(targetUrl);
		uiObject.put("response", sysMsg);
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(uiObject));

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		String failureUrl = getExceptionMappings().getProperty(failed.getClass().getName(),
				getAuthenticationFailureUrl());
		if (logger.isDebugEnabled()) {
			logger.debug("Authentication request failed: ", failed);
		}
		try {
			request.getSession().setAttribute(ACEGI_SECURITY_LAST_EXCEPTION_KEY, failed);
		} catch (Exception ignored) {
		}

		onUnsuccessfulAuthentication(request, response, failed);

		getRememberMeServices().loginFail(request, response);
		Map<String, Object> uiObject = new HashMap<String, Object>();
		SysMsg sysMsg = new SysMsg();
		sysMsg.setResult("failure");
		if ("Bad credentials".equals(failed.getMessage())) {
			sysMsg.setMessage("用户密码错误");
		} else {
			sysMsg.setMessage("您暂无权限");
		}
		sysMsg.setTargetUrl(failureUrl);
		uiObject.put("response", sysMsg);
		response.setContentType("text/plain;charset=UTF-8");
		String res = JSON.toJSONString(uiObject);
		response.getWriter().write(res);
	}

}
