package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GAdApp;

@Service
public interface GAdAppService {
	void add(GAdApp adApp);
	void delete(Long id);
	void update(GAdApp adApp);
	GAdApp find(Long id);
	GAdApp findByAdId(Long adId);
}
