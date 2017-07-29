package com.ohh.util;

import com.ohh.config.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSON;

public class ResponseUtil {
	/**
	 * ���󷵻����ݴ���
	 * @param res
	 * @return
	 */
	public static ResponseEntity<String> general(CommonResponse res){
		return new ResponseEntity<String>(JSON.toJSONString(res), HttpStatus.OK);
	}
	
	/**
	 * �ɹ�����
	 * @param data
	 * @return
	 */
	public static ResponseEntity<String> success(Object data){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_SUCCESS);
		res.setData(data);
		return general(res);
	}
	
	public static ResponseEntity<String> success(){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_SUCCESS);
		return general(res);
	}
	
	public static ResponseEntity<String> success(String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_SUCCESS_MSG);
		res.setMsg(msg);
		return general(res);
	}
	
	/**
	 * �����׳��쳣
	 * @param msg
	 * @return
	 */
	public static ResponseEntity<String> exception(String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_EXCEPTION);
		res.setMsg(msg);
		return general(res);
	}
	
	public static ResponseEntity<String> unKonwException(){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_EXCEPTION);
		res.setMsg("���Ժ�����");
		return general(res);
	}
	
	/**
	 * �Զ���
	 * @param code
	 * @param msg
	 * @return
	 */
	public static ResponseEntity<String> custom(Integer code, String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(code);
		res.setMsg(msg);
		return general(res);
	}
}
