package com.ohh.config;

public class Constant {

	/**
	 * �������󷵻���
	 */
	public static final int RESCODE_SUCCESS = 1000;				//�ɹ�
	public static final int RESCODE_SUCCESS_MSG = 1001;			//�ɹ�(�з�����Ϣ)
	public static final int RESCODE_EXCEPTION = 1002;			//�����׳��쳣
	public static final int RESCODE_NOLOGIN = 1003;				//δ��½״̬
	public static final int RESCODE_NOEXIST = 1004;				//��ѯ���Ϊ��
	public static final int RESCODE_NOAUTH = 1005;				//�޲���Ȩ��
	
	/**
	 * jwt
	 */
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
}
