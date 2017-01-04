package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GFilterApp;

@Service
public interface GFilterAppService {
	void add(GFilterApp filterApp);
	void delete(Long id);
	void update(GFilterApp filterApp);
	GFilterApp find();
}
