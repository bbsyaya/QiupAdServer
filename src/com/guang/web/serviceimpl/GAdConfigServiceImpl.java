package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdConfig;
import com.guang.web.service.GAdConfigService;
@Service
public class GAdConfigServiceImpl implements GAdConfigService{
	@Resource private DaoTools daoTools;
	public void add(GAdConfig appWhiteList) {
		daoTools.add(appWhiteList);
	}

	public void delete(Long id) {
		daoTools.delete(GAdConfig.class, id);
	}

	public void update(GAdConfig appWhiteList) {
		daoTools.update(appWhiteList);
	}

	public GAdConfig find(Long id) {
		return daoTools.find(GAdConfig.class, id);
	}

	public QueryResult<GAdConfig> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GAdConfig.class, null, null, firstindex, 100, lhm);
	}

	public GAdConfig find() {
		List<GAdConfig> list = daoTools.find(GAdConfig.class, "open", 1+"", 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
}
