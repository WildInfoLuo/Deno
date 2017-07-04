package com.demo.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.UserDao;
import com.demo.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	public User load(Long id) {
		// TODO Auto-generated method stub
		return (User)this.getCurrentSession().get(User.class,id);
	}
	
	/*
	 * /(non-Javadoc)
	 * @see com.demo.dao.UserDao#login(java.lang.String, java.lang.String)
	 * 验证用户名 和 密码 
	 */
	public boolean login(String userName, String paw) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(User.class); 
		
		cri.add(Restrictions.eq("userId",userName));
		cri.add(Restrictions.eq("passWord",paw));
 
		Object ob = cri.list();
		if (ob == null|| cri.list().size()==0){
			return false;
		} 
		return true;
	}
	public boolean save(User user) {
		// TODO Auto-generated method stub
		this.getCurrentSession().save(user);
		this.getCurrentSession().flush();
		return true;
	}
}
