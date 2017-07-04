package com.demo.view;

import java.util.ArrayList;
import java.util.List;

import com.demo.entity.Project;

public class ProjectDetail {
	public List<String> list=new ArrayList<String>();
	Project project;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
