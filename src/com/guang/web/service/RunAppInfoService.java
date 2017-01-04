package com.guang.web.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GatherAppInfo;
import com.guang.web.mode.GatherAppRunInfo;

@Service
public interface RunAppInfoService {
	void add(GatherAppRunInfo gatherAppRunInfo);
	void delete(long id);
	QueryResult<GatherAppRunInfo> findAll();
	QueryResult<GatherAppRunInfo> findAlls(int firstindex);
	QueryResult<GatherAppRunInfo> find(LinkedHashMap<String, String> colvals);
}
