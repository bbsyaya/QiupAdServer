package com.guang.web.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GPOffer;

@Service
public interface GPOfferService {
	void add(GPOffer offer);
	void delete(Long id);
	void update(GPOffer offer);
	GPOffer find(Long id);
	QueryResult<GPOffer> findAlls();
	QueryResult<GPOffer> findAllsByPriority();
	QueryResult<GPOffer> findAlls(int firstindex);
	QueryResult<GPOffer> find(LinkedHashMap<String, String> colvals);

}
