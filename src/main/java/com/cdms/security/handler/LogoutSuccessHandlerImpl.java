package com.cdms.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cdms.pojo.response.JsonResponseBody;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCode(200);
		responseBody.setMessage("登出成功");
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(responseBody));

	}

}
