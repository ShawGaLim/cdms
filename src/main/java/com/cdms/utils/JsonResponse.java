package com.cdms.utils;

import java.util.List;

import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.pojo.response.ResponseData;

public class JsonResponse {

	public static JsonResponseBody responseList(List<?> list, Integer total) {
		ResponseData data = new ResponseData();
		JsonResponseBody responseBody = new JsonResponseBody();
		
		data.setItems(list);
		data.setTotal(total);
		
		responseBody.setCode(200);
		responseBody.setMessage("操作成功");
		responseBody.setData(data);
		
		return responseBody; 
	}
	
	public static JsonResponseBody responseSuccess() {
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCode(200);
		responseBody.setMessage("操作成功");
		return responseBody;
	}
	
	public static JsonResponseBody responseFailure(String reason) {
		JsonResponseBody responseBody = new JsonResponseBody();
		responseBody.setCode(400);
		responseBody.setMessage(reason);
		return responseBody;
	}
}
