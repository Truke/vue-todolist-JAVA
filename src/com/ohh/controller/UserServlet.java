package com.ohh.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ohh.model.Users;
import com.ohh.service.UserOp;
import com.ohh.service.impl.UserOpImpl;
import com.ohh.model.Users;
import com.ohh.service.UserOp;
import com.ohh.service.impl.UserOpImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public UserServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("connecte")==null||!((String) session.getAttribute("connecte")).equals("true")){
			response.sendRedirect("login");
		}else{
			UserOp userOp = new UserOpImpl();
			Users user = new Users();
			String user_name = (String) session.getAttribute("login");
			user=userOp.getUsersInfo(user_name);
			
			request.setAttribute("information", user);
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
