package com.thinkerwolf.blog.common.security.intercept.web;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;
import org.acegisecurity.intercept.web.FilterInvocationDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

import com.thinkerwolf.blog.common.log.InternalLoggerFactory;
import com.thinkerwolf.blog.common.log.Logger;
import com.thinkerwolf.blog.common.security.service.PermissionService;

/**
 * 数据库实现路径数据库实现匹配
 * 
 * @author Bruce Wu
 * 
 */
public class DBFilterInvocationDefinitionSource extends AbstractFilterInvocationDefinitionSource
		implements FilterInvocationDefinition {

	private static final Logger logger = InternalLoggerFactory.getLogger(DBFilterInvocationDefinitionSource.class);
	/**  */
	private boolean convertUrlToLowercaseBeforeComparison = false;

	private List<String> secureUrls;

	private PathMatcher pathMatcher = new AntPathMatcher();

	@Autowired
	private PermissionService permissionService;
	/** 是否使用ANT路径匹配 */
	private boolean isUseAntPath = true;

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setSecureUrls(List<String> secureUrls) {
		this.secureUrls = secureUrls;
	}

	@Override
	public boolean isConvertUrlToLowercaseBeforeComparison() {
		return this.convertUrlToLowercaseBeforeComparison;
	}

	@Override
	public void setConvertUrlToLowercaseBeforeComparison(boolean convertUrlToLowercaseBeforeComparison) {
		this.convertUrlToLowercaseBeforeComparison = convertUrlToLowercaseBeforeComparison;
	}

	public boolean isUseAntPath() {
		return isUseAntPath;
	}

	public void setUseAntPath(boolean isUseAntPath) {
		this.isUseAntPath = isUseAntPath;
	}

	@Override
	public ConfigAttributeDefinition lookupAttributes(String url) {
		Assert.notNull(permissionService, "permissionService must be initialized");
		int index = url.lastIndexOf("?");
		if (index > 0) {
			url = url.substring(0, index);
		}
		if (isConvertUrlToLowercaseBeforeComparison()) {
			url = url.toLowerCase();
		}

		if (isSecureUrl(url)) {
			if (logger.isDebugEnabled()) {
				logger.debug("@@@@@ Security url " + url);
			}
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("@@@@@ url " + url);
		}
		List<String> authorities = permissionService.getAuthorityFromCache(url);
		if (authorities == null || authorities.size() <= 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("@@@@@ no authorities found");
			}

			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			configAttrEditor.setAsText("");
			return (ConfigAttributeDefinition) configAttrEditor.getValue();

			// return null;
		} else {
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			String authoritiesStr = "";
			for (Iterator<String> iter = authorities.iterator(); iter.hasNext();) {
				String authority = iter.next();
				authoritiesStr += authority + ",";
			}
			String authStr = authoritiesStr.substring(0, authoritiesStr.length() - 1);

			if (logger.isDebugEnabled()) {
				logger.debug("@@@ authStr " + authStr);
			}
			configAttrEditor.setAsText(authStr);
			// 组装并返回ConfigAttributeDefinition
			return (ConfigAttributeDefinition) configAttrEditor.getValue();
		}
	}

	/**
	 * 判断当前url是否为安全路径
	 * 
	 * @param url
	 * @return
	 */
	protected boolean isSecureUrl(String url) {
		if (secureUrls != null && secureUrls.size() > 0) {
			for (String secuUrl : secureUrls) {
				if (isConvertUrlToLowercaseBeforeComparison()) {
					secuUrl = secuUrl.toLowerCase();
				}
				if (pathMatcher.match(secuUrl, url)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void addSecureUrl(String expression, ConfigAttributeDefinition attr) {

	}

	protected class EntryHolder {
		private ConfigAttributeDefinition configAttributeDefinition;
		private String antPath;

		public EntryHolder(String antPath, ConfigAttributeDefinition attr) {
			this.antPath = antPath;
			this.configAttributeDefinition = attr;
		}

		protected EntryHolder() {
			throw new IllegalArgumentException("Cannot use default constructor");
		}

		public String getAntPath() {
			return antPath;
		}

		public ConfigAttributeDefinition getConfigAttributeDefinition() {
			return configAttributeDefinition;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator getConfigAttributeDefinitions() {
		return null;
	}

}
