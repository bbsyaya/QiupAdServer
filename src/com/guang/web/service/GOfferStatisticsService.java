package com.guang.web.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOfferStatistics;

@Service
public interface GOfferStatisticsService {
	void add(GOfferStatistics statistics);
	void delete(long id);
	void update(GOfferStatistics statistics);
	GOfferStatistics find(long id);
	GOfferStatistics findToday();
	GOfferStatistics findYesterday();
	QueryResult<GOfferStatistics> findAll(int firstindex);
	QueryResult<GOfferStatistics> findAll(LinkedHashMap<String, String> colvals,int firstindex);
}
