package com.ohh.service.impl;

import com.ohh.dao.UserDao;
import com.ohh.dao.impl.UserDaoImpl;
import com.ohh.model.Users;
import com.ohh.service.UserSignIn;

public class UserSignInImpl implements UserSignIn {
	UserDao userdao=new UserDaoImpl();
	
	@Override
	public boolean checkIdentity(Users user){
		Users tmp = new Users();
		String name = user.getUserName();
		tmp = userdao.getUserInfoByName(name); 
		if(tmp!=null&&tmp.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}
	
	
}
