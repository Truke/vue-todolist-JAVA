package com.ohh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/exitApi")
public class ExitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ExitServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = null;
		String jsonStr = "{\"success\":true,\"msg\":\"ÒÑÍË³öµÇÂ¼\"}";
		if(session.getAttribute("connecte")!=null||((String) session.getAttribute("connnecte")).equals("true")){
			session.setAttribute("connecte", "false");
			session.removeAttribute("connecte");
			session.removeAttribute("login");
		}
		try {
		    out = response.getWriter();
		    out.write(jsonStr);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
	}
}
