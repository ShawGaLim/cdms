package com.cdms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cdms.security.handler.AccessDeniedHandlerImpl;
import com.cdms.security.handler.AuthenticationEntryPointImpl;
import com.cdms.security.handler.AuthenticationFailureHandlerImpl;
import com.cdms.security.handler.AuthenticationProviderImpl;
import com.cdms.security.handler.AuthenticationSuccessHandlerImpl;
import com.cdms.security.handler.LogoutSuccessHandlerImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPointImpl entryPoint;
	
	@Autowired
	private AuthenticationFailureHandlerImpl failureHandler;
	
	@Autowired
	private AuthenticationSuccessHandlerImpl successHandler;
	
	@Autowired
	private AccessDeniedHandlerImpl accessDeniedHandler;
	
	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandler;
	
	@Autowired
	private AuthenticationProviderImpl provider;
	
	@Autowired
	private JwtAuthenticationTokenFilter jwtTokenFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.csrf()
		.disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic()
		.authenticationEntryPoint(entryPoint)
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
        .and()	
        .formLogin()
        .permitAll()
        .and()
        .logout()
        .logoutSuccessHandler(logoutSuccessHandler)
        .permitAll();
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
	}
	
	@Bean
	MyAuthenticationFilter myAuthenticationFilter() throws Exception {
		MyAuthenticationFilter filter = new MyAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(successHandler);
		filter.setAuthenticationFailureHandler(failureHandler);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}
	
}
