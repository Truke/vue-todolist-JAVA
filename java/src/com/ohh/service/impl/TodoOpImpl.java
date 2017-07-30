package com.ohh.service.impl;

import java.util.List;

import com.ohh.dao.TodoDao;
import com.ohh.dao.impl.TodoDaoImpl;
import com.ohh.model.Todos;
import com.ohh.service.TodoOp;

public class TodoOpImpl implements TodoOp {
	TodoDao tododao = new TodoDaoImpl();
	
	@Override
	public boolean addTodo(Todos todo){
		if(tododao.addTodo(todo))
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean delTodo(Long id){
		if(tododao.delTodo(id))
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean updateTodo(Long id,Boolean status){
		if(tododao.updateTodo(id,status))
			return true;
		else 
			return false;
	}
	
	@Override
	public List<Todos> findTodos(Long user_id){
		return tododao.findTodos(user_id);
	}
	
}
