package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GFilterApp;
import com.guang.web.service.GFilterAppService;

@Service
public class GFilterAppServiceImpl implements GFilterAppService {
	@Resource private DaoTools daoTools;
	
	public void add(GFilterApp filterApp) {
		daoTools.add(filterApp);
	}

	public void delete(Long id) {
		daoTools.delete(GFilterApp.class, id);
	}

	public void update(GFilterApp filterApp) {
		daoTools.update(filterApp);
	}

	public GFilterApp find() {
		List<GFilterApp> list = daoTools.find(GFilterApp.class, null, null, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

}
