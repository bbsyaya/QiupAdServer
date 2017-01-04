package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GatherAppInfo;
import com.guang.web.mode.GatherAppRunInfo;
import com.guang.web.service.RunAppInfoService;

@Service
public class RunAppInfoServiceImpl implements RunAppInfoService {

	@Resource DaoTools daoTools;
	public QueryResult<GatherAppRunInfo> findAlls(int firstindex) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GatherAppRunInfo.class, null, null, firstindex, 20, lhm);
	}

	public QueryResult<GatherAppRunInfo> find(LinkedHashMap<String, String> colvals) {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryResult<GatherAppRunInfo> findAll() {
		// TODO Auto-generated method stub
		return daoTools.find(GatherAppRunInfo.class, null, null, 0, 1000000, null);
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		daoTools.delete(GatherAppRunInfo.class, id);
	}

	public void add(GatherAppRunInfo gatherAppRunInfo) {
		daoTools.add(gatherAppRunInfo);
	}
}
