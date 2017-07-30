package com.ohh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ohh.util.ResponseUtil;

@WebServlet("/logoutApi")
public class SignOutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public SignOutServlet() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String result = "";
		if(session.getAttribute("login")!=null){
			session.invalidate();
		}
		result = ResponseUtil.success().getBody();
		response.getWriter().write(result);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		doGet(request, response);
	}
}
