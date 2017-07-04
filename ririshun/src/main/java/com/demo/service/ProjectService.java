package com.demo.service;

import java.util.List;

import com.demo.entity.Project;

public interface ProjectService {
	public List<Project> findPage(int page,int rows,Project project);
	public Long getCount(Project project);
	public Project load(Long id);
	public void  update(Project project);
	
	
	//获取 项目对应的供应商id
	public List<String> getProjectId(Long id);
}
