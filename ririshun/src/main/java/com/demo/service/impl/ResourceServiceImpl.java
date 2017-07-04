package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ResourceDao;
import com.demo.entity.Resource;
import com.demo.service.ResourceService;
 

@Service("resourceService")
public class ResourceServiceImpl   implements ResourceService{
	@Autowired
	private ResourceDao  resourceDao;
	public List<Resource> getResourceAll() {
		// TODO Auto-generated method stub
		return resourceDao.getResourceAll();
	}

}
