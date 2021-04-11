package com.cdms.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;

public class WebSocketEncoder implements Encoder.Text<Object> {

	@Override
	public void init(EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public String encode(Object object) throws EncodeException {
		try {
			return JSON.toJSONString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
