package com.demo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.ProviderDao;
import com.demo.entity.Provider;
import com.demo.util.TooL;

@Repository("providerDao")
public class ProviderDaoImpl implements ProviderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public List<Provider> findPage(int page, int rows, Provider provider) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Provider.class); 
		
		if(null!=provider.getProvider_name()&&!"".equals(provider.getProvider_name())){
			cri.add(Expression.or(Expression.like("provider_name","%"+provider.getProvider_name()+"%")));
		}
		if(null!=provider.getCreateBy()&&!"".equals(provider.getCreateBy())){
			cri.add(Restrictions.eq("createBy",provider.getCreateBy()));
		}
		
 		TooL.addTimeBetweenStament(cri,"createDate",provider.getStartTimeQuery(),provider.getEndTimeQuery());
		cri.setFirstResult( (page-1) * rows);
		cri.setMaxResults(rows);
		Object ob = cri.list();
		if (ob == null){
			return null;
		} 
		return (List<Provider>) ob;
	}
	public Long getCount(Provider provider) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Provider.class); 
		 
		if(null!=provider.getProvider_name()&&!"".equals(provider.getProvider_name())){
			cri.add(Expression.or(Expression.like("provider_name","%"+provider.getProvider_name()+"%")));
		}
		if(null!=provider.getCreateBy()&&!"".equals(provider.getCreateBy())){
			cri.add(Restrictions.eq("createBy",provider.getCreateBy()));
		}
		
 		TooL.addTimeBetweenStament(cri,"createDate",provider.getStartTimeQuery(),provider.getEndTimeQuery());
		
		Object ob = cri.list();
		if (ob == null){
			return  0L;
		} 
		Long a=new Long(cri.list().size());
		return  a;
		 
	}

	public boolean validateName(String name) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Provider.class); 
		 
		if(null!=name&&!"".equals(name.trim())){
			cri.add(Restrictions.eq("provider_name",name));
 		}
		Object ob = cri.list();
		if (ob != null&&cri.list().size()>0){
			return  false;
		} 
		return true;
	}

	public void add(Provider provider) {
		// TODO Auto-generated method stub
		this.getCurrentSession().save(provider);
		this.getCurrentSession().flush();
	}

	public List<Provider> findAll(Provider provider) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Provider.class);  
 		TooL.addTimeBetweenStament(cri,"modifyDate",provider.getStartTimeQuerym(),provider.getEndTimeQuerym());

		Object ob = cri.list();
		if (ob == null){
			return null;
		} 
		return (List<Provider>) ob;
	}

	public List<String> findAll(Long id) {
		// TODO Auto-generated method stub
		String sql =" select e.provider_name from  demo_project_provider f left join demo_provider e on  f.provider=e.id where f.project="+id;
		Query query=this.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	public List<String> findAllID(Long id) {
		// TODO Auto-generated method stub
		String sql =" select   f.provider from  demo_project_provider f left join demo_provider e on  f.provider=e.id where f.project="+id;
		Query query=this.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	public Provider load(Long id) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Provider.class);  
		cri.add(Restrictions.eq("id",Integer.parseInt(id+"")));
		Object ob = cri.uniqueResult();
		if (ob == null){
			return null;
		} 
		return (Provider) ob;
	}

	public Provider load(Provider provider) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Provider.class);  
		cri.add(Restrictions.eq("provider_name",provider.getProvider_name()));
		Object ob = cri.uniqueResult();
		if (ob == null){
			return null;
		} 
		return (Provider) ob;
	}

	public void update(Provider provider) {
		// TODO Auto-generated method stub
		this.getCurrentSession().update(provider);
		this.getCurrentSession().flush();
	}

}
