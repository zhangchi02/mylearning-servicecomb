package com.zhc.common;

import java.util.Date;

/**
* @author zhangchi02
* @date 2019年3月5日
*/
public class GreetingResponse {

	private String Msg;
	private Date timestamp;
	
	public GreetingResponse(){}


	public GreetingResponse(String msg, Date timestamp) {
		super();
		Msg = msg;
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
