package com.ohh.service.impl;

import com.ohh.dao.UserDao;
import com.ohh.dao.impl.UserDaoImpl;
import com.ohh.model.Users;
import com.ohh.service.UserOp;

public class UserOpImpl implements UserOp {
	UserDao userdao=new UserDaoImpl();
	
	@Override
	public boolean updateUserInfo(Users user){
		if(userdao.addUser(user))
			return true;
		else 
			return false;
	}
	
	@Override
	public Users getUsersInfo(String userName){
		Users user = new Users();
		user=userdao.getUserInfoByName(userName);
		return user;
	}
	
}
