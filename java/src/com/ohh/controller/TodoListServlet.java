package com.ohh.controller;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ohh.model.Todos;
import com.ohh.model.Users;
import com.ohh.service.TodoOp;
import com.ohh.service.impl.TodoOpImpl;
import com.ohh.util.JwtUtil;
import com.ohh.util.ResponseUtil;


@WebServlet("/todolistApi")
public class TodoListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public TodoListServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader.startsWith("Bearer ")){
			String bearerToken = authorizationHeader.substring(7);
			JwtUtil jwt = new JwtUtil();
			String result = "";
			try {
				Claims claims = jwt.parseJWT(bearerToken);
				String json = claims.getSubject();
				Users user = JSONObject.parseObject(json, Users.class);
				TodoOp todoop = new TodoOpImpl();
				List<Todos> list = todoop.findTodos(user.getId());
				result = ResponseUtil.success(list).getBody();
			} catch (Exception e) {
				e.printStackTrace();
				result = ResponseUtil.unKonwException().getBody();
			}
			response.getWriter().write(result);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader.startsWith("Bearer ")){
			String bearerToken = authorizationHeader.substring(7);
			JwtUtil jwt = new JwtUtil();
			try {
				Claims claims = jwt.parseJWT(bearerToken);
				String json = claims.getSubject();
				Users user = JSONObject.parseObject(json, Users.class);
				switch (method) {
				case "add":
					doAddTodo(request,response);
					break;
				case "del":
					doDelTodo(request,response);
					break;
				case "update":
					doUpdateTodo(request,response);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	protected void doAddTodo(HttpServletRequest request, HttpServletResponse response){
		String result = "";
		try {
			String payloadRequest = GetPayloadBody.GetPayloadBody(request);
			JSONObject jsonObj = JSONObject.parseObject(payloadRequest);
			Long user_id = jsonObj.getLong("id");
			String content = jsonObj.getString("content");
			Boolean status = jsonObj.getBoolean("status");
			TodoOp todoop = new TodoOpImpl();
			Todos todo = new Todos();
			todo.setUser_id(user_id);
			todo.setContent(content);
			todo.setStatus(status);
			todoop.addTodo(todo);
			result = ResponseUtil.success().getBody();
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelTodo(HttpServletRequest request, HttpServletResponse response){
		String result = "";
		try {
			String payloadRequest = GetPayloadBody.GetPayloadBody(request);
			JSONObject jsonObj = JSONObject.parseObject(payloadRequest);
			Long id = jsonObj.getLong("id");
			TodoOp todoop = new TodoOpImpl();
			todoop.delTodo(id);
			result = ResponseUtil.success().getBody();
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doUpdateTodo(HttpServletRequest request, HttpServletResponse response){
		String result = "";
		try {
			String payloadRequest = GetPayloadBody.GetPayloadBody(request);
			JSONObject jsonObj = JSONObject.parseObject(payloadRequest);
			Long id = jsonObj.getLong("id");
			Boolean status = jsonObj.getBoolean("status");
			TodoOp todoop = new TodoOpImpl();
			todoop.updateTodo(id,status);
			result = ResponseUtil.success().getBody();
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
