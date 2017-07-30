package com.ohh.dao;

import java.util.List;

import com.ohh.model.Todos;

public interface TodoDao {
	
	public boolean addTodo(Todos todo);
	
	public boolean delTodo(Long id);
	
	public boolean updateTodo(Long id,Boolean status);
	
	public List<Todos> findTodos(Long user_id);
}
