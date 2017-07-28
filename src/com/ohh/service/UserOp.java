package com.ohh.service;

import com.ohh.model.Users;

public interface UserOp {
	
	public boolean updateUserInfo(Users user);
	
	public Users getUsersInfo(String userName);
}
