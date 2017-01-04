package com.guang.web.service;

import com.guang.web.mode.GPermission;


public interface GPermissionService {
	void add(GPermission permission);
	void delete(long id);
	void update(GPermission permission);
	GPermission find(long id);
}
