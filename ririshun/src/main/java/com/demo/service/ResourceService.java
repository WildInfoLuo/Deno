package com.demo.service;

import java.util.List;

import com.demo.entity.Resource;

public interface ResourceService {
	/*
	 * 获取全部资源 暂无权限控制 
	 */
	public List<Resource> getResourceAll();
}
