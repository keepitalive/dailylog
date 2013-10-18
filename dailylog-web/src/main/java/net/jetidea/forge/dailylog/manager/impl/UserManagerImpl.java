package net.jetidea.forge.dailylog.manager.impl;

import net.jetidea.forge.dailylog.GenericException;
import net.jetidea.forge.dailylog.dao.UserDao;
import net.jetidea.forge.dailylog.entity.User;
import net.jetidea.forge.dailylog.manager.UserManager;
import net.jetidea.forge.dailylog.utils.MD5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
