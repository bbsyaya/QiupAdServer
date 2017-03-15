package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTBSdkConfig;
import com.guang.web.service.GTBSdkConfigService;
@Service
public class GTBSdkConfigServiceImpl implements GTBSdkConfigService{
	@Resource private DaoTools daoTools;
	public void add(GTBSdkConfig config) {
		daoTools.add(config);
	}

	public void delete(long id) {
		daoTools.delete(GTBSdkConfig.class, id);
	}

	public void update(GTBSdkConfig config) {
		daoTools.update(config);
	}

	public GTBSdkConfig find(long id) {
		return daoTools.find(GTBSdkConfig.class, id);
	}

	public GTBSdkConfig find(String channel) {
		List<GTBSdkConfig> list = daoTools.find(GTBSdkConfig.class, "channel", channel, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GTBSdkConfig> find(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTBSdkConfig.class, null, null, firstindex, 100, lhm);
	}

	public QueryResult<GTBSdkConfig> findAll() {
		return daoTools.find(GTBSdkConfig.class, null, null, 0, 1000000, null);
	}

}
