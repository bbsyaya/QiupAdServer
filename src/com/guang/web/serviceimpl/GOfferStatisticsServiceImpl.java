package com.guang.web.serviceimpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOfferStatistics;
import com.guang.web.service.GOfferStatisticsService;
@Service
public class GOfferStatisticsServiceImpl implements GOfferStatisticsService{
	@Resource private DaoTools daoTools;
	public void add(GOfferStatistics statistics) {
		daoTools.add(statistics);
	}

	public void delete(long id) {
		daoTools.delete(GOfferStatistics.class, id);
	}

	public void update(GOfferStatistics statistics) {
		daoTools.update(statistics);
	}

	public GOfferStatistics find(long id) {
		return daoTools.find(GOfferStatistics.class, id);
	}

	public GOfferStatistics findToday() {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		Date date = new Date();	
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		String from = date.toLocaleString();
		date.setDate(date.getDate()+1);
		String to = date.toLocaleString();
		
		colvals.put("stime between", "'"+from+"'" + " and " + "'"+to+"'");
		List<GOfferStatistics> list = daoTools.find(GOfferStatistics.class, colvals, 0, 1, null).getList();
		if(list != null && list.size() > 0)
		return list.get(0);
		return null;
	}

	public GOfferStatistics findYesterday() {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		Date date = new Date();	
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		date.setDate(date.getDate()-1);
		
		String from = date.toLocaleString();
		date.setDate(date.getDate()+1);
		String to = date.toLocaleString();
		
		colvals.put("stime between", "'"+from+"'" + " and " + "'"+to+"'");
		List<GOfferStatistics> list = daoTools.find(GOfferStatistics.class, colvals, 0, 1, null).getList();
		if(list != null && list.size() > 0)
		return list.get(0);
		return null;
	}

	public QueryResult<GOfferStatistics> findAll(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOfferStatistics.class, null, null, firstindex, 100, lhm);
	}

	public QueryResult<GOfferStatistics> findAll(
			LinkedHashMap<String, String> colvals, int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOfferStatistics.class, colvals, firstindex, 100, lhm);
	}

}
