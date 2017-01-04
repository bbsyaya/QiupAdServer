package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GNetworkOperator;
import com.guang.web.service.GNetworkOperatorService;


@Service
public class GNetworkOperatorServiceImpl implements GNetworkOperatorService{
	@Resource private DaoTools daoTools;


	public void add(GNetworkOperator networkOperator) {
		try {
			if(find(networkOperator.getName()) == null)
				daoTools.add(networkOperator);
		} catch (Exception e) {
		}
	}

	public GNetworkOperator find(int id) {
		return daoTools.find(GNetworkOperator.class, id);
	}

	public GNetworkOperator find(String name) {
		List<GNetworkOperator> list = daoTools.find(GNetworkOperator.class, "name", name, 0, 1, null).getList();
		if(list != null && list.size() > 0)
		return list.get(0);
		return null;
	}

	public QueryResult<GNetworkOperator> findAll() {
		return daoTools.find(GNetworkOperator.class, null, null, 0, 100, null);
	}

}
