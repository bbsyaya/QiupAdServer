package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdmin;

@Service
public interface GAdminService {
	void add(GAdmin admin);
	void delete(long id);
	void update(GAdmin admin);
	GAdmin find(long id);
	GAdmin find(String name);
	QueryResult<GAdmin> findAll();
}
