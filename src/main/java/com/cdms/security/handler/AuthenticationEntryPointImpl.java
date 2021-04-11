package com.cdms.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cdms.pojo.response.JsonResponseBody;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCode(000);
		responseBody.setMessage("未登录");
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(responseBody));
		
	}

}
