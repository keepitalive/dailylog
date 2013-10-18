package net.jetidea.forge.dailylog.dao;

import net.jetidea.forge.dailylog.entity.User;

public interface UserDao {

	User findByUserName(String username);
}
