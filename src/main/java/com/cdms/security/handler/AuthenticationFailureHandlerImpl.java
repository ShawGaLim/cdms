package com.cdms.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cdms.pojo.response.JsonResponseBody;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCode(400);
		responseBody.setMessage("登录失败");
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(responseBody));
		
	}

}
