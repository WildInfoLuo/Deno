package com.demo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.ResourceDao;
import com.demo.entity.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl implements ResourceDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	public List<Resource> getResourceAll() {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Resource.class); 
		cri.add(Restrictions.eq("resourceType",0));
		cri.addOrder(Order.asc("id"));
		Object ob = cri.list();
		if (ob!= null&&cri.list().size()>0){
			return  (List<Resource>)cri.list();
		}
		return null;
	}

}
