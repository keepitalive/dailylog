package net.jetidea.forge.dailylog.dao.impl;

import java.util.List;

import net.jetidea.forge.dailylog.dao.UserDao;
import net.jetidea.forge.dailylog.entity.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


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
