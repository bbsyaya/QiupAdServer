package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GUserStt;

@Service
public interface GUserSttService {
	void add(GUserStt userStt);
	GUserStt find();
	void update(GUserStt userStt);
}
