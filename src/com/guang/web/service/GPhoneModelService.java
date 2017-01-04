package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GPhoneModel;


@Service
public interface GPhoneModelService {

	void add(GPhoneModel phoneModel);
	
	GPhoneModel find(int id);
	
	GPhoneModel find(String model);
	
	QueryResult<GPhoneModel> findAll();
}
