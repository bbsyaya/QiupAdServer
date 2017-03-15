package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTBSdkConfig;

@Service
public interface GTBSdkConfigService {
	void add(GTBSdkConfig config);
	void delete(long id);
	void update(GTBSdkConfig config);
	GTBSdkConfig find(long id);
	GTBSdkConfig find(String channel);
	QueryResult<GTBSdkConfig> find(int firstindex);
	QueryResult<GTBSdkConfig> findAll();
}
