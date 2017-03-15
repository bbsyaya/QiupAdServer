package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTBAdId;

@Service
public interface GTBAdIdService {
	void add(GTBAdId adId);
	void delete(long id);
	void update(GTBAdId adId);
	GTBAdId find(long id);
	GTBAdId find(String adId);
	QueryResult<GTBAdId> find(int firstindex);
	QueryResult<GTBAdId> find(int type,String channel);
	QueryResult<GTBAdId> findAll();
}
