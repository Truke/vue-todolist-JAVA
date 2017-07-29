package com.ohh.dao;

import java.util.List;
import com.ohh.model.Users;

public interface UserDao {
	
	public boolean getUserName(String name);
	
	public boolean getUserEmail(String email);
	
	public Users getUserInfoByName(String name);
	
	public boolean updateUserInfo(Users user);
	
	public boolean addUser(Users user);
}
