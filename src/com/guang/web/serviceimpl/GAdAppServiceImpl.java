package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GAdApp;
import com.guang.web.service.GAdAppService;
@Service
public class GAdAppServiceImpl implements GAdAppService{
	@Resource private DaoTools daoTools;
	public void add(GAdApp adApp) {
		daoTools.add(adApp);
	}

	public void delete(Long id) {
		daoTools.delete(GAdApp.class, id);
	}

	public void update(GAdApp adApp) {
		daoTools.update(adApp);
	}

	public GAdApp find(Long id) {
		return daoTools.find(GAdApp.class, id);
	}

	public GAdApp findByAdId(Long adId) {
		List<GAdApp> list = daoTools.find(GAdApp.class, "adId", adId+"", 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

}
