package com.blog.security.service;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.providers.rememberme.RememberMeAuthenticationToken;
import org.acegisecurity.ui.AuthenticationDetailsSource;
import org.acegisecurity.ui.AuthenticationDetailsSourceImpl;
import org.acegisecurity.ui.logout.LogoutHandler;
import org.acegisecurity.ui.rememberme.RememberMeServices;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 自定义RememberMe服务
 * 
 * @author Bruce Wu
 * 
 */
public class AcgeiRememberMeServices implements RememberMeServices,
		InitializingBean, LogoutHandler {

	public static final String ACGEI_REMEMBERME_COOKIE_NAME = "_acegi_rememberme_cookie_name";

	protected Logger logger = Logger.getLogger(AcgeiRememberMeServices.class);

	/**
	 * 存储在Cookie中的key
	 */
	private String key = "remember";

	/**
	 * 永远记住我
	 */
	private boolean alwaysRememberMe;

	private String cookieName = ACGEI_REMEMBERME_COOKIE_NAME;

	protected long tokenValiditySeconds = 1209600; // 14 days

	private String parameter = "j_rememberme";

	protected AuthenticationDetailsSource authenticationDetailsSource = new AuthenticationDetailsSourceImpl();

	private UserDetailsService userDetailsService;

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public void setAlwaysRememberMe(boolean alwaysRememberMe) {
		this.alwaysRememberMe = alwaysRememberMe;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@SuppressWarnings("deprecation")
	@Override
	public Authentication autoLogin(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0) {
			return null;
		}
		// MD5加密 将Cookie存储成 admin;123;validutytime=long
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				String cookieValue = cookie.getValue();
				if (Base64.isArrayByteBase64(cookieValue.getBytes())) {
					if (logger.isDebugEnabled()) {
						logger.debug("检测到Cookie... ");
					}
					cookieValue = new String(Base64.decodeBase64(cookieValue));
					String[] cookiesDetails = cookieValue.split(";");
					if (cookies.length == 3) {
						long validityTime;
						try {
							validityTime = new Long(cookiesDetails[2])
									.longValue();
						} catch (Exception e) {
							cancelCookie(request, response, "不合法的时间");
							return null;
						}
						if (System.currentTimeMillis() > validityTime) {
							cancelCookie(request, response, "cookie已过期");
							return null;
						}
						UserDetails userDetails = loadUserDetails(request,
								response, cookiesDetails);
						if (!userDetails.isAccountNonExpired()) {
							cancelCookie(request, response, "账户已过期");
							return null;
						}
						if (!userDetails.isAccountNonLocked()) {
							cancelCookie(request, response, "账户已锁定");
							return null;
						}
						if (!userDetails.isCredentialsNonExpired()) {
							cancelCookie(request, response, "账户无权限");
							return null;
						}
						if (!userDetails.isEnabled()) {
							cancelCookie(request, response, "账户已停用");
							return null;
						}
						// By this stage we have a valid token
						if (logger.isDebugEnabled()) {
							logger.debug("Remember-me cookie accepted");
						}
						RememberMeAuthenticationToken auth = new RememberMeAuthenticationToken(
								this.key, userDetails,
								userDetails.getAuthorities());
						auth.setDetails(authenticationDetailsSource
								.buildDetails((HttpServletRequest) request));
						return auth;
					} else {
						logger.debug("Cookie 数量不为3，取消Cookie登陆");
						return null;
					}

				}

			}
		}
		return null;
	}

	@Override
	public void loginFail(HttpServletRequest request,
			HttpServletResponse response) {
		cancelCookie(request, response, "Interactive authentication attempt was unsuccessful");
	}

	@Override
	public void loginSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication successfulAuthentication) {
		// 先判断是否是RememberMe请求,将请求放入Cookie中
		logger.debug("@@@@@ loginSuccess ");
		if (!rememberRequest(request, parameter)) {
			logger.debug("非rememberMe请求");
			return;
		}
		// Determine username and password, ensuring empty strings
		Assert.notNull(successfulAuthentication.getPrincipal());
		Assert.notNull(successfulAuthentication.getCredentials());

		String username = retrieveUserName(successfulAuthentication);
		String password = retrievePassword(successfulAuthentication);

		// If unable to find a username and password, just abort as
		// TokenBasedRememberMeServices unable to construct a valid token in
		// this case
		if (!StringUtils.hasLength(username)
				|| !StringUtils.hasLength(password)) {
			return;
		}

		long expiryTime = System.currentTimeMillis()
				+ (tokenValiditySeconds * 1000);
		String tokenValue = username+ ";" + password + ";" + expiryTime;
		String tokenValueBase64 = new String(Base64.encodeBase64(tokenValue
				.getBytes()));
		response.addCookie(makeValidCookie(tokenValueBase64, request,
				tokenValiditySeconds));

		if (logger.isDebugEnabled()) {
			logger.debug("Added remember-me cookie for user '" + username
					+ "', expiry: '" + new Date(expiryTime) + "'");
		}
	}

	protected Cookie makeValidCookie(String tokenValueBase64, HttpServletRequest request, long maxAge) {
		Cookie cookie = new Cookie(cookieName, tokenValueBase64);
		cookie.setMaxAge(new Long(maxAge).intValue());
		cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");

		return cookie;
	}
	
	protected String retrieveUserName(Authentication successfulAuthentication) {
		if (isInstanceOfUserDetails(successfulAuthentication)) {
			return ((UserDetails) successfulAuthentication.getPrincipal())
					.getUsername();
		} else {
			return successfulAuthentication.getPrincipal().toString();
		}
	}

	protected String retrievePassword(Authentication successfulAuthentication) {
		if (isInstanceOfUserDetails(successfulAuthentication)) {
			return ((UserDetails) successfulAuthentication.getPrincipal())
					.getPassword();
		} else {
			return successfulAuthentication.getCredentials().toString();
		}
	}

	private boolean isInstanceOfUserDetails(Authentication authentication) {
		return authentication.getPrincipal() instanceof UserDetails;
	}

	protected UserDetails loadUserDetails(HttpServletRequest request,
			HttpServletResponse response, String[] cookieTokens) {
		UserDetails userDetails = null;

		try {
			userDetails = this.userDetailsService
					.loadUserByUsername(cookieTokens[0]);
		} catch (UsernameNotFoundException notFound) {
			cancelCookie(request, response,
					"Cookie token[0] contained username '" + cookieTokens[0]
							+ "' but was not found");

			return null;
		}
		return userDetails;
	}

	protected void cancelCookie(HttpServletRequest request,
			HttpServletResponse response, String reasonForLog) {
		if ((reasonForLog != null) && logger.isDebugEnabled()) {
			logger.debug("Cancelling cookie for reason: " + reasonForLog);
		}
		response.addCookie(makeCancelCookie(request));
	}

	protected Cookie makeCancelCookie(HttpServletRequest request) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request
				.getContextPath() : "/");

		return cookie;
	}

	protected boolean rememberRequest(HttpServletRequest request,
			String parameter) {
		if (this.alwaysRememberMe) {
			return true;
		}
		parameter = request.getParameter(parameter);
		if(logger.isDebugEnabled()) {
			logger.debug("@@@@@ is Remember Request ?? " + parameter);
		}
		boolean c = false;
		try {
			c = Boolean.valueOf(parameter);
		} catch (Exception e) {
			return false;
		}
		return c;
	}

}
