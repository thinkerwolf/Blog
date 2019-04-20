package com.thinkerwolf.blog.security.config;

import com.thinkerwolf.blog.security.service.MemberUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/signup").permitAll()
                .antMatchers("/topic/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "/error/**", "/topic/**", "/font-awesome/**", "/font/**").permitAll()
                .antMatchers("/api/member/**", "/api/article/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        MemberUserDetailsService memberUserDetailsService = new MemberUserDetailsService();
        return memberUserDetailsService;
    }
    
    
    
}
