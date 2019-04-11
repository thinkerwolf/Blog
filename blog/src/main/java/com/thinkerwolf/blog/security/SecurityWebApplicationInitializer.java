package com.thinkerwolf.blog.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Automatically register the springSecurityFilterChain Filter for every URL in your application
   Add a ContextLoaderListener that loads the WebSecurityConfig.
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer(Class<?>... configurationClasses) {
        super(configurationClasses);
    }

}
