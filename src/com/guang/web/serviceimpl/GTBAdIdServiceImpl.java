package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTBAdId;
import com.guang.web.service.GTBAdIdService;
@Service
public class GTBAdIdServiceImpl implements GTBAdIdService{
	@Resource private DaoTools daoTools;
	public void add(GTBAdId adId) {
		daoTools.add(adId);
	}

	public void delete(long id) {
		daoTools.delete(GTBAdId.class, id);
	}

	public void update(GTBAdId adId) {
		daoTools.update(adId);
	}

	public GTBAdId find(long id) {
		return daoTools.find(GTBAdId.class, id);
	}

	public QueryResult<GTBAdId> findAll() {
		return daoTools.find(GTBAdId.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GTBAdId> find(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTBAdId.class, null, null, firstindex, 100, lhm);
	}

	public GTBAdId find(String adId) {
		List<GTBAdId> list = daoTools.find(GTBAdId.class, "adId", adId, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GTBAdId> find(int type,String channel) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTBAdId.class, "type", type+"","channel",channel, 0, 10000000, lhm);
	}

}
