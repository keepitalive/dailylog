package com.dailylog.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailylog.GenericException;
import com.dailylog.dao.UserDao;
import com.dailylog.entity.User;
import com.dailylog.manager.UserManager;
import com.dailylog.utils.MD5;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDao userDao;

	@Override
	public User login(String username, String password) throws GenericException {

		User user = userDao.findByUserName(username);

		if (null == user) {
			throw new GenericException("User is not exist.");
		} else if (user.getPassword().equals(MD5.encrypt(password))) {
			return user;
		} else {
			throw new GenericException("Password is incorrect.");
		}
	}

}
