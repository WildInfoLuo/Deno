package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProjectDao;
import com.demo.entity.Project;
import com.demo.entity.Provider;
import com.demo.service.ProjectService;


@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectDao   projectDao;
 	public List<Project> findPage(int page, int rows, Project project) {
		// TODO Auto-generated method stub
		return projectDao.findPage(page, rows, project);
	}

	public Long getCount(Project project) {
		// TODO Auto-generated method stub
		return projectDao.getCount(project);
	}

	public Project load(Long id) {
		// TODO Auto-generated method stub
		return projectDao.load(id);
	}

	public List<String> getProjectId(Long id) {
		// TODO Auto-generated method stub
		return projectDao.getProjectId(id);
	}

	public void update(Project project) {
		// TODO Auto-generated method stub
		
		projectDao.update(project);
	}

}
