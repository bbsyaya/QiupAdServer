package com.guang.web.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GatherAppInfo;

@Service
public interface GGatherDataService {
	void add(GatherAppInfo gatherAppInfo);
	void delete(long id);
	QueryResult<GatherAppInfo> findAll();
	QueryResult<GatherAppInfo> findAlls(int index);
	QueryResult<GatherAppInfo> find(LinkedHashMap<String, String> colvals);
}
