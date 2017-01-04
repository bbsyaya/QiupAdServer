package com.guang.web.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GPermission;
import com.guang.web.service.GPermissionService;
@Service
public class GPermissionServiceImpl implements GPermissionService{
	@Resource
	private DaoTools daoTools;
	public void add(GPermission permission) {
		daoTools.add(permission);
	}

	public void delete(long id) {
		daoTools.delete(GPermission.class, id);
	}

	public void update(GPermission permission) {
		daoTools.update(permission);
	}

	public GPermission find(long id) {
		return daoTools.find(GPermission.class, id);
	}

}
