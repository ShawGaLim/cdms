package com.cdms.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.pojo.response.ResponseData;
import com.cdms.security.JwtTokenUtil;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		ResponseData data = new ResponseData();
		JsonResponseBody responseBody = new JsonResponseBody();
		String token = JwtTokenUtil.generateToken((String)authentication.getPrincipal());

		data.setToken(token);	
		
		responseBody.setCode(200);
		responseBody.setMessage("登录成功");
		responseBody.setData(data);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(responseBody));
		
	}

}
