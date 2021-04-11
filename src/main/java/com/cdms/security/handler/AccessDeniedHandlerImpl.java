package com.cdms.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cdms.pojo.response.JsonResponseBody;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCode(300);
		responseBody.setMessage("无权访问");
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(responseBody));
		
	}

}
