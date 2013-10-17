package com.dailylog.dao;

import com.dailylog.entity.User;

public interface UserDao {

	User findByUserName(String username);
}
