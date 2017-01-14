package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GAdPositionConfig;
import com.guang.web.service.GAdPositionConfigService;

@Service
public class GAdPositionConfigServiceImpl implements  GAdPositionConfigService{
	@Resource private DaoTools daoTools;

	public void add(GAdPositionConfig adPositionConfig) {
		daoTools.add(adPositionConfig);
	}

	public void delete(Long id) {
		daoTools.delete(GAdPositionConfig.class, id);
	}

	public void update(GAdPositionConfig adPositionConfig) {
		daoTools.update(adPositionConfig);
	}

	public GAdPositionConfig find(Long id) {
		return daoTools.find(GAdPositionConfig.class, id);
	}

	public GAdPositionConfig findByPositionId(Long id) {
		List<GAdPositionConfig> list = daoTools.find(GAdPositionConfig.class, "adPositionId", id+"", 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
}
