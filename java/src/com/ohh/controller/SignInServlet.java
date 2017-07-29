package com.ohh.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.sf.json.JSONObject;
import com.ohh.controller.GetPayloadBody;

import com.ohh.model.Users;
import com.ohh.service.UserSignIn;
import com.ohh.service.impl.UserSignInImpl;

import com.ohh.config.Constant;
import com.ohh.util.JwtUtil;
import com.ohh.util.ResponseUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@WebServlet("/loginApi")
public class SignInServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private JwtUtil jwt;
	
	public SignInServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String payloadRequest = GetPayloadBody.GetPayloadBody(request);
		JSONObject jsonObj = JSONObject.fromObject(payloadRequest);
		String username = jsonObj.getString("username");
		String password = jsonObj.getString("password");
		try{
		if(!username.equals("")&&!password.equals("")){
			UserSignIn userSign=new UserSignInImpl();
			Users user=new Users();
			user.setUserName(username);
			user.setPassword(password);
			if(userSign.checkIdentity(user)){
				HttpSession session = request.getSession();
//				session.setAttribute("connecte", "true");
//				session.setAttribute("login", user.getUserName());
				String subject = JwtUtil.generalSubject(user);
				String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
				String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
				JSONObject jo = new JSONObject();
				jo.put("token", token);
				jo.put("refreshToken", refreshToken);
				ResponseUtil.success(jo);
				ResponseEntity<String> kk = ResponseUtil.success(jo);
			}else{
//				request.setAttribute("error", "error");
				ResponseUtil.exception("用户名或密码错误");
			}
		}else{
			ResponseUtil.exception("用户名或密码不能为空");
		}
		}catch(Exception e){
			ResponseUtil.unKonwException();
		}
	}
}
