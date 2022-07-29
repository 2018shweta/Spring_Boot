package com.bean;

public class ResponseBean<T> {

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	private String msg;
	T data;
	
	
	
}
