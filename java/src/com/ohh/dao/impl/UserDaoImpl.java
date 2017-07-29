package com.ohh.dao.impl;

import com.ohh.dao.UserDao;
import com.ohh.model.Users;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDaoImpl implements UserDao{
	Session session = new Configuration().configure().buildSessionFactory().openSession();
	Transaction transaction = session.beginTransaction();
	
	@Override
	public boolean getUserName(String name){
		List<String> list = new ArrayList<String>();
		try{
			list = session.createQuery("select u from Users as u where u.userName like ?").setParameter(0, name).list();
		}catch(Exception e){
			return false;
		}
		if(list==null)
			return false;
		else if(list.size()==0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean getUserEmail(String email){
		List<String> list = new ArrayList<String>();
		try{
			list = session.createQuery("select u from Users as u where u.email like ?").setParameter(0, email).list();
		}catch(Exception e){
			return false;
		}
		if(list==null)
			return false;
		else if(list.size()==0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean updateUserInfo(Users user){
		try{
			session.update(user);
			transaction.commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean addUser(Users user){
		try{
			session.save(user);
			transaction.commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public Users getUserInfoByName(String name){
		Users user = new Users();
		try{
			user = (Users) session.get(Users.class,name);
		}catch(Exception e){
			e.printStackTrace();
			return user;
		}
		return user;
	}
}
