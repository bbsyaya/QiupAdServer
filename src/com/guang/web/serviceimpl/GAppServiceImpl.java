package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GApp;
import com.guang.web.service.GAppService;


@Service
public class GAppServiceImpl implements GAppService{

	@Resource private DaoTools daoTools;
	
	
	public void add(GApp ad) {
		daoTools.add(ad);
	}

	public void delete(Long id) {
		daoTools.delete(GApp.class, id);
	}

	public void update(GApp ad) {
		daoTools.update(ad);
	}

	public GApp find(Long id) {
		return daoTools.find(GApp.class, id);
	}


	public QueryResult<GApp> findApps(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GApp.class, null, null, firstindex, 20, lhm);
	}
	
	public QueryResult<GApp> findApps(int firstindex,int maxIndex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GApp.class, null, null, firstindex, maxIndex, lhm);
	}

	public QueryResult<GApp> findAppsByUserId(long userId) {
		return daoTools.find(GApp.class, "userId", userId+"", 0, 30, null);
	}

}
