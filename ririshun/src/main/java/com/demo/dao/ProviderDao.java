package com.demo.dao;

import java.util.List;

import com.demo.entity.Provider;

public interface ProviderDao{
	public List<Provider> findPage(int page,int rows,Provider provider);
	public Long getCount(Provider provider);
	public boolean validateName(String name);
	public void add(Provider provider);
	public   List<Provider>  findAll(Provider provider);
	public List<String> findAll(Long  id);
	List<String> findAllID(Long id);
	public Provider load(Long id);
	public Provider load(Provider  provider);
	public void update(Provider provider);
}
