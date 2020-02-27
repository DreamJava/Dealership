package com.revature.service;

import com.revature.dao.*;
import com.revature.model.User;

public class UserServiceImpl implements UserService {

	private static UserDao uDao = new UserDaoPostgres();
	
	@Override
	public void updatePassword(String username, String password) {
		
		//uDao.createUser(new User(, username, password));
		
	}

	@Override
	public User loginUser(String username, String password) {

		User u = uDao.getUserByUsername(username);
		
		if (u != null && u.getPassword().equals(password)) {
			return u;
		}
		
		return null;
	}

}
