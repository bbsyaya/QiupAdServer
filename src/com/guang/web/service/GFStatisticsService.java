package com.guang.web.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GStatistics;

@Service
public interface GFStatisticsService {
	void add(GStatistics statistics);
	void delete(Long id);
	void update(GStatistics statistics);
	GStatistics find(Long id);
	List<GStatistics> findAlls(int firstindex);
	List<GStatistics> findAlls(LinkedHashMap<String, String> colvals);
	long findAllsNum();
	long findNum(LinkedHashMap<String, String> colvals,Date from,Date to);
	long findNum2(LinkedHashMap<String, String> colvals,Date from,Date to);
}
