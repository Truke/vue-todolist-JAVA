package com.ohh.util;

public class CommonResponse {
	private Integer code;	//������
	private String msg;		//������ʾ��Ϣ
	private Object data;	//��������
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}