package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArea;
import com.guang.web.service.GAreaService;


@Service
public class GAreaServiceImpl implements GAreaService {
	@Resource private DaoTools daoTools;

	public void add(GArea area) {
		try {
			if(find(area.getProvince(), area.getCity()) == null)
				daoTools.add(area);
		} catch (Exception e) {
		}
	}

	public GArea find(int id) {
		return daoTools.find(GArea.class, id);
	}

	public QueryResult<GArea> findAll() {
		return daoTools.find(GArea.class, null, null, 0, 10000, null);
	}

	public GArea find(String province, String city) {
		List<GArea> list = daoTools.find(GArea.class, "province", province, "city", city, 0, 1, null).getList();
		if(list != null && list.size() > 0)
		return list.get(0);
		return null;
	}

}
