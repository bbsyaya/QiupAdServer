package com.guang.web.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GUserStt;
import com.guang.web.service.GUserSttService;

@Service
public class GUserSttServiceImpl implements GUserSttService{
	@Resource private DaoTools daoTools;

	public void add(GUserStt userStt) {
		daoTools.add(userStt);
	}

	public GUserStt find() {
		return daoTools.find(GUserStt.class, 1l);
	}

	public void update(GUserStt userStt) {
		daoTools.update(userStt);
	}
}
