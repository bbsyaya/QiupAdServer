package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdmin;
import com.guang.web.service.GAdminService;

@Service
public class GAdminServiceImpl implements GAdminService{
	@Resource private DaoTools daoTools;
	public void add(GAdmin admin) {
		daoTools.add(admin);
	}

	public void delete(long id) {
		daoTools.delete(GAdmin.class, id);
	}

	public void update(GAdmin admin) {
		daoTools.update(admin);
	}

	public GAdmin find(long id) {
		return daoTools.find(GAdmin.class, id);
	}

	public GAdmin find(String name) {
		List<GAdmin> list = daoTools.find(GAdmin.class, "name", name, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GAdmin> findAll() {
		return daoTools.find(GAdmin.class, null, null, 0, 1000000, null);
	}

}
