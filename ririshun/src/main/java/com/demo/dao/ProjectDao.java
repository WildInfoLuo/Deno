package com.demo.dao;

import java.util.List;

import com.demo.entity.Project;

public interface  ProjectDao {
	public List<Project> findPage(int page,int rows,Project project);
	public Long getCount(Project project);
	
	public Project load(Long id) ;
	public void update(Project project);
	List<String> getProjectId(Long id);
}
