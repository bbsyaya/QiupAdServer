package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GAdPositionConfig;

@Service
public interface GAdPositionConfigService {
	void add(GAdPositionConfig adPositionConfig);
	void delete(Long id);
	void update(GAdPositionConfig adPositionConfig);
	GAdPositionConfig find(Long id);
	GAdPositionConfig findByPositionId(Long id);
}
