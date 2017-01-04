package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArea;

@Service
public interface GAreaService {

	void add(GArea area);
	
	GArea find(int id);
	GArea find(String province,String city);
	QueryResult<GArea> findAll();
}
