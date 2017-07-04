package com.demo.service;

import java.util.List;

import com.demo.entity.Provider;

public interface ProviderService {
	public List<Provider> findPage(int page,int rows,Provider provider);
	public Long getCount(Provider provider);
	
	public boolean validateName(String name);
	public void add(Provider provider);
	public void update(Provider provider);
	public  Provider  load(Long id);
	public  Provider  load(Provider  provider);
	 

	public List<Provider> findAll(Provider provider);
	
	//根据项目ID 获取已经选择的所有供应商 信息名称 
	public List<String> findAll(Long  id);
	public List<String> findAllID(Long  id);
}
