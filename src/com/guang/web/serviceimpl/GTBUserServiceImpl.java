package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GTBUser;
import com.guang.web.service.GTBUserService;

@Service
public class GTBUserServiceImpl implements GTBUserService{
	@Resource private DaoTools daoTools;
	public void add(GTBUser user) {
		daoTools.add(user);
	}

	public void delete(Long id) {
		daoTools.delete(GTBUser.class, id);
	}

	public void update(GTBUser user) {
		daoTools.update(user);
	}

	public GTBUser find(Long id) {
		return daoTools.find(GTBUser.class, id);
	}

	public GTBUser findByImei(String imei) {
		List<GTBUser> list = daoTools.find(GTBUser.class,"imei",imei, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

}
