package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

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

	public GMedia findByPackageName(String packageName) {
		List<GMedia> list = daoTools.find(GMedia.class, "packageName", packageName, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
}
