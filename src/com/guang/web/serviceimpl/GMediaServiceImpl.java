package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GMedia;
import com.guang.web.service.GMediaService;
@Service
public class GMediaServiceImpl implements GMediaService{
	@Resource private DaoTools daoTools;
	public void add(GMedia media) {
		daoTools.add(media);
	}

	public void delete(Long id) {
		daoTools.delete(GMedia.class, id);
	}

	public void update(GMedia media) {
		daoTools.update(media);
	}

	public GMedia find(Long id) {
		return daoTools.find(GMedia.class, id);
	}

	public QueryResult<GMedia> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GMedia.class, null, null, firstindex, 20, lhm);
	}

	public QueryResult<GMedia> findAlls() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GMedia.class, null, null, 0, 100, lhm);
	}
}
