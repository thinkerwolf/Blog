package com.blog.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.event.authentication.InteractiveAuthenticationSuccessEvent;

import com.blog.security.modal.SysMsg;

/**
 * 鑷畾涔堿uthentication 灏嗛噸瀹氬悜 鍙栨秷鎺�
 * 
 * @author Bruce Wu
 * 
 */
public class AuthenticationProcessingFilter extends
		org.acegisecurity.ui.webapp.AuthenticationProcessingFilter {
	
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException {
		// TODO Auto-generated method stub
		super.onSuccessfulAuthentication(request, response, authResult);
		//灏嗙敤鎴蜂俊鎭瓨鍌ㄨ嚦Session涓�
		request.getSession().setAttribute("j_username", obtainUsername(request));
		
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException {
		// TODO Auto-generated method stub
		super.onUnsuccessfulAuthentication(request, response, failed);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Authentication Successful authReult " + authResult);
		}
		SecurityContextHolder.getContext().setAuthentication(authResult);

		if (logger.isDebugEnabled()) {
			logger.debug("Updated SecurityContextHolder to contain the following Authentication: '"
					+ authResult + "'");
		}

		String targetUrl = isAlwaysUseDefaultTargetUrl() ? null
				: obtainFullRequestUrl(request);

		if (targetUrl == null) {
			targetUrl = getDefaultTargetUrl();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Redirecting to target URL from HTTP Session (or default): "
					+ targetUrl);
		}

		onSuccessfulAuthentication(request, response, authResult);

		getRememberMeServices().loginSuccess(request, response, authResult);

		if (this.eventPublisher != null) {
			eventPublisher
					.publishEvent(new InteractiveAuthenticationSuccessEvent(
							authResult, this.getClass()));
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
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException {
		String failureUrl = getExceptionMappings().getProperty(failed.getClass()
				.getName(), getAuthenticationFailureUrl());
		if (logger.isDebugEnabled()) {
			logger.debug("Authentication request failed: " + failed.toString());
		}

		try {
			request.getSession().setAttribute(
					ACEGI_SECURITY_LAST_EXCEPTION_KEY, failed);
		} catch (Exception ignored) {
		}

		onUnsuccessfulAuthentication(request, response, failed);

		getRememberMeServices().loginFail(request, response);
		Map<String, Object> uiObject = new HashMap<String, Object>();
		SysMsg sysMsg = new SysMsg();
		sysMsg.setResult("failure");
		if("Bad credentials".equals(failed.getMessage()))
			sysMsg.setMessage("当前用户暂无权限");
		else 
			sysMsg.setMessage("您暂无权限");
		sysMsg.setTargetUrl(failureUrl);
		uiObject.put("response", sysMsg);
		response.setContentType("text/plain;charset=UTF-8");
		String res= JSON.toJSONString(uiObject);
		response.getWriter().write(res);
	}

}
