package com.thinkerwolf.blog.system;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.thinkerwolf.blog.common.generator.IdGeneratorManager;
import com.thinkerwolf.blog.common.generator.SnowflakeIdGenerator;

/**
 * 项目初始化管理器
 * 
 * @author wukai
 *
 */
@Component
public class InitManager implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1, 1);
		IdGeneratorManager.getInstance().addIdGenerator(snowflakeIdGenerator);
		
		
		
	}

}
