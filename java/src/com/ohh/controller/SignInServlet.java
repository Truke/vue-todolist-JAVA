package com.ohh.controller;

import java.io.IOException;

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
		String result = "";
		try{
			if(!username.equals("")&&!password.equals("")){
				UserSignIn userSign=new UserSignInImpl();
				Users user=new Users();
				user.setUserName(username);
				user.setPassword(password);
				Users tmp = userSign.checkIdentity(user);
				if(tmp!=null){
					HttpSession session = request.getSession();
					session.setAttribute("login", user.getUserName());
					String subject = JwtUtil.generalSubject(tmp);
					jwt = new JwtUtil();
					String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
					String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
					JSONObject jo = new JSONObject();
					jo.put("token", token);
					jo.put("refreshToken", refreshToken);
					result = ResponseUtil.success(jo).getBody();
				}else{
					result = ResponseUtil.exception("用户名或密码错误").getBody();
				}
			}else{
				result = ResponseUtil.exception("用户名或密码不能为空").getBody();
			}
		}catch(Exception e){
			e.printStackTrace();
			result = ResponseUtil.unKonwException().getBody();
		}
		response.getWriter().write(result);
	}
}
