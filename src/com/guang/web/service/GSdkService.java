package com.guang.web.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GSdk;

@Service
public interface GSdkService {
	void add(GSdk sdk);
	void delete(Long id);
	void update(GSdk sdk);
	GSdk find(Long id);
	GSdk findFirst(String channel);
	GSdk findNew(String channel);
	GSdk findNew(String packageName,String channel);
	QueryResult<GSdk> findAlls(int firstindex);
	QueryResult<GSdk> findAlls();
	QueryResult<GSdk> find(LinkedHashMap<String, String> colvals);
}
