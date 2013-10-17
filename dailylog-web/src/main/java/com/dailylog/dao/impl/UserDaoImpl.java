package com.dailylog.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dailylog.dao.UserDao;
import com.dailylog.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(String username) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from User u where u.usrname=:name";

		Query query = session.createQuery(hql);
		query.setString("name", username);

		List<User> result = query.list();

		return (User) (result.size() > 0 ? result.get(0) : null);
	}
}
