package com.thinkerwolf.blog.common.log.jdk;

import com.thinkerwolf.blog.common.log.InternalLoggerFactory;
import com.thinkerwolf.blog.common.log.Logger;

public class JdkLoggerFactory extends InternalLoggerFactory {

    public Logger createLogger(String name) {
        return new JdkLogger(java.util.logging.Logger.getLogger(name));
    }

    public Logger createLogger(Class<?> clazz) {
        return new JdkLogger(java.util.logging.Logger.getLogger(clazz.getName()));
    }
}
