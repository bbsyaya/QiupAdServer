package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GMedia;
@Service
public interface GMediaService {
	void add(GMedia media);
	void delete(Long id);
	void update(GMedia media);
	GMedia find(Long id);
	GMedia findByPackageName(String packageName);
	QueryResult<GMedia> findAlls(int firstindex);
	QueryResult<GMedia> findAlls();
}
