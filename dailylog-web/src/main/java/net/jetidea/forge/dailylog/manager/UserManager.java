package net.jetidea.forge.dailylog.manager;

import net.jetidea.forge.dailylog.GenericException;
import net.jetidea.forge.dailylog.entity.User;

public interface UserManager {

	User login(String username, String password) throws GenericException;
}
