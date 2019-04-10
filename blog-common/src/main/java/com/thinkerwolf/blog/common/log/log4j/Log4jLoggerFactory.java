package com.thinkerwolf.blog.common.log.log4j;

import org.apache.logging.log4j.LogManager;

import com.thinkerwolf.blog.common.log.InternalLoggerFactory;
import com.thinkerwolf.blog.common.log.Logger;

public class Log4jLoggerFactory extends InternalLoggerFactory {



    public Logger createLogger(String name) {
        return new Log4jLogger(LogManager.getLogger(name));
    }

    public Logger createLogger(Class<?> clazz) {
        return new Log4jLogger(LogManager.getLogger(clazz));
    }
}
