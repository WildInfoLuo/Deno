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

import com.demo.dao.ProjectDao;
import com.demo.entity.Project;
import com.demo.util.TooL;

@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public List<Project> findPage(int page, int rows, Project project) {
		// TODO Auto-generated method stub
		Criteria cri = this.getCurrentSession().createCriteria(Project.class); 
		
		if(null!=project.getProject_name()&&!"".equals(project.getProject_name())){
			cri.add(Expression.or(Expression.like("project_name","%"+project.getProject_name()+"%")));
		}
		 
		if(null!=project.getIfSelect()&&!"".equals(project.getIfSelect())){
			cri.add(Restrictions.eq("ifSelect",project.getIfSelect()));
		}
		if(null!=project.getCreateBy()&&!"".equals(project.getCreateBy())){
			cri.add(Restrictions.eq("createBy",project.getCreateBy()));
		}
 		TooL.addTimeBetweenStament(cri,"createDate",project.getStartTimeQuery(),project.getEndTimeQuery());
 		TooL.addTimeBetweenStament(cri,"modifyDate",project.getStartTimeQueryS(),project.getEndTimeQueryS());
		cri.setFirstResult( (page-1) * rows);
		cri.setMaxResults(rows);
		Object ob = cri.list();
		if (ob == null){
			return null;
		} 
		return (List<Project>) ob;
	}
	public Long getCount(Project project) {
		// TODO Auto-generated method stub
Criteria cri = this.getCurrentSession().createCriteria(Project.class); 
		
		if(null!=project.getProject_name()&&!"".equals(project.getProject_name())){
			cri.add(Expression.or(Expression.like("project_name","%"+project.getProject_name()+"%")));
		}
		if(null!=project.getCreateBy()&&!"".equals(project.getCreateBy())){
			cri.add(Restrictions.eq("createBy",project.getCreateBy()));
		}
 		TooL.addTimeBetweenStament(cri,"createDate",project.getStartTimeQuery(),project.getEndTimeQuery());
	 
		Object ob = cri.list();
		if (ob == null){
			return 0L;
		} 
		Long a=new Long( cri.list().size());
		return  a;
	}
	
	
	public Project load(Long id) {
		// TODO Auto-generated method stub
		Query query=this.getCurrentSession().createQuery(" from Project where id="+id);
		return (Project)query.uniqueResult();
	}


	public List<String> getProjectId(Long id) {
		// TODO Auto-generated method stub
		String sql=" select provider from  DEMO_PROJECT_PROVIDER where project="+id;
		Query query=this.getCurrentSession().createSQLQuery(sql);
		List<String> list=query.list();
		return list;
	}

	public void update(Project project) {
		// TODO Auto-generated method stub
		//
		 this.getCurrentSession().saveOrUpdate(project);
		 //删除 
		 String dsql= " delete from  DEMO_PROJECT_PROVIDER where project="+project.getId();
		 String asql=" insert into DEMO_PROJECT_PROVIDER(provider,project) values(?,?) ";
		 Query query=this.getCurrentSession().createSQLQuery(dsql);
		 query.executeUpdate();
		 Query queryd=this.getCurrentSession().createSQLQuery(asql);
		 if(null!=project.getProjectids()||!"".equals(project.getProjectids())){
			 String a[]=project.getProjectids().split(",");
			 for(int i=0;i<a.length;i++){
				 queryd.setString(1,project.getId().toString());
				 queryd.setString(0,a[i]);
				 queryd.executeUpdate();
			 } 
		 }
		 this.getCurrentSession().flush();
	}

}
