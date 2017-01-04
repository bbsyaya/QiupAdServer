package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GApp;



@Service
public interface GAppService {

	void add(GApp app);
	void delete(Long id);
	void update(GApp app);
	GApp find(Long id);
	QueryResult<GApp> findApps(int firstindex);
	QueryResult<GApp> findApps(int firstindex,int maxIndex);
	QueryResult<GApp> findAppsByUserId(long userId);
}
