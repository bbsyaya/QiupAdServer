package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GTBUser;

@Service
public interface GTBUserService {
	void add(GTBUser user);
	void delete(Long id);
	void update(GTBUser user);
	GTBUser find(Long id);
	GTBUser findByImei(String imei);
}
