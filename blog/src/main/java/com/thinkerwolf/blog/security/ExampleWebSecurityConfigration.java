package com.thinkerwolf.blog.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
public class ExampleWebSecurityConfigration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.httpBasic();
		/* xml 配置
		<http>
	    	<intercept-url pattern="/**" access="authenticated"/>
	    	<form-login />
	    	<http-basic />
	    </http>
		*/
		
		// 自定义formPage
		 http
	        .authorizeRequests()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login") 
	            .permitAll();
		 
		// 鉴权配置
		 http
	        .authorizeRequests()                                                                
	            .antMatchers("/**").permitAll()                  
	            .antMatchers("/commont/**").authenticated()                                                
	            .and()
	        .formLogin()
	        	.loginPage("/login");
		 
		 // logout 配置
		 http
		 	.logout()
		 	.logoutUrl("logout")
		 	.logoutSuccessUrl("logoutsuccess")
		 	//.logoutSuccessHandler(logoutSuccessHandler);
		 	.invalidateHttpSession(true)
		 	//.addLogoutHandler(logoutHandler)
		 	.deleteCookies("", "")
		 	.and();
		 
		 /*
		 常用的LogoutHandler实现。 
		 PersistentTokenBasedRememberMeServices
		 TokenBasedRememberMeServices
		 CookieClearingLogoutHandler
		 CsrfLogoutHandler
		 SecurityContextLogoutHandler*/
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	

}
