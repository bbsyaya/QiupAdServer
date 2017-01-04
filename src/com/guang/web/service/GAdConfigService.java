package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdConfig;

@Service
public interface GAdConfigService {
	void add(GAdConfig adConfig);
	void delete(Long id);
	void update(GAdConfig adConfig);
	GAdConfig find(Long id);
	GAdConfig find();
	QueryResult<GAdConfig> findAlls(int firstindex);
}
