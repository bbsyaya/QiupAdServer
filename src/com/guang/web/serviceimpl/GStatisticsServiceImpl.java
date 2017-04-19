package com.guang.web.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArea;
import com.guang.web.mode.GStatistics;
import com.guang.web.mode.GUser;
import com.guang.web.service.GStatisticsService;
@Service
public class GStatisticsServiceImpl implements GStatisticsService{
	@Resource private DaoTools daoTools;
	public void add(GStatistics statistics) {
		daoTools.add(statistics);
	}

	public void delete(Long id) {
		daoTools.delete(GStatistics.class, id);
	}

	public void update(GStatistics statistics) {
		daoTools.update(statistics);
	}

	public GStatistics find(Long id) {
		return daoTools.find(GStatistics.class, id);
	}

	public QueryResult<GStatistics> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GStatistics.class, null, null, firstindex, 100, lhm);
	}

	public QueryResult<GStatistics> findAlls(
			LinkedHashMap<String, String> colvals) {
		return daoTools.find(GStatistics.class, colvals, 0, 100000000, null);
	}

	public long findAllsNum(LinkedHashMap<String, String> colvals) {
		List<String> fileds = new ArrayList<String>();
		fileds.add("userId");
		return daoTools.findNum(GStatistics.class, fileds, colvals);
	}
	public long findAllsNum2(LinkedHashMap<String, String> colvals) {
		return daoTools.findNum(GStatistics.class, colvals);
	}
}
