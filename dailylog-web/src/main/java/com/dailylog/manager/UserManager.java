package com.dailylog.manager;

import com.dailylog.GenericException;
import com.dailylog.entity.User;

public interface UserManager {

	User login(String username, String password) throws GenericException;
}
