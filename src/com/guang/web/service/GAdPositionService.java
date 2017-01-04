package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
@Service
public interface GAdPositionService {
	void add(GAdPosition adPosition);
	void delete(Long id);
	void update(GAdPosition adPosition);
	GAdPosition find(Long id);
	GAdPosition find(int adPositionType);
	QueryResult<GAdPosition> findAlls(int firstindex);
	QueryResult<GAdPosition> findAlls();
}
