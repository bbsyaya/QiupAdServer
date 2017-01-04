package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GNetworkOperator;


@Service
public interface GNetworkOperatorService {
	
	void add(GNetworkOperator networkOperator);

	GNetworkOperator find(int id);

	GNetworkOperator find(String name);

	QueryResult<GNetworkOperator> findAll();
}
