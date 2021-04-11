package com.cdms.pojo;

import lombok.Data;

@Data
public class Notice {

	private Integer id;
	private String name;
	private String content;
	private User user;
	private String createtime;
	
}
