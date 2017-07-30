package com.ohh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ohh.dao.TodoDao;
import com.ohh.model.Todos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TodoDaoImpl implements TodoDao {
	Session session = new Configuration().configure().buildSessionFactory().openSession();
	Transaction transaction = session.beginTransaction();
	
	@Override
	public boolean addTodo(Todos todo){
		try{
			session.save(todo);
			transaction.commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delTodo(Long id){
		try{
			Todos todo = (Todos) session.get(Todos.class, id);
			session.delete(todo);
			transaction.commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateTodo(Long id,Boolean status){
		try{
			Todos todo = (Todos) session.get(Todos.class, id);
			todo.setStatus(status);
			session.update(todo);
			transaction.commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public List<Todos> findTodos(Long user_id){
		List list = new ArrayList();
		try{
			list = session.createQuery("select l from Todos as l where l.user_id = ?").setParameter(0, user_id).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
