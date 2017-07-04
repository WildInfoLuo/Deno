package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProviderDao;
import com.demo.entity.Provider;
import com.demo.service.ProviderService;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderDao  providerDao;
		
	public List<Provider> findPage(int page, int rows, Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.findPage(page, rows, provider);
	}

	public Long getCount(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.getCount(provider);
	}

	public boolean validateName(String name) {
		// TODO Auto-generated method stub
		return providerDao.validateName(name);
	}

	public void add(Provider provider) {
		// TODO Auto-generated method stub
		providerDao.add(provider);
	}

	public List<Provider> findAll(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.findAll(provider);
	}

	public List<String> findAll(Long id) {
		// TODO Auto-generated method stub
		return providerDao.findAll(id);
	}

	public List<String> findAllID(Long id) {
		// TODO Auto-generated method stub
		return providerDao.findAllID(id);
	}

	public Provider load(Long id) {
		// TODO Auto-generated method stub
		return providerDao.load( id);
	}

	public Provider load(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.load(provider);
	}

	public void update(Provider provider) {
		// TODO Auto-generated method stub
		  providerDao.update(provider);
	}

	
}
