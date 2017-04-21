package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOffer;
import com.guang.web.service.GOfferService;
@Service
public class GOfferServiceImpl implements GOfferService{
	@Resource private DaoTools daoTools;
	public void add(GOffer offer) {
		daoTools.add(offer);
	}

	public void delete(Long id) {
		daoTools.delete(GOffer.class, id);
	}

	public void update(GOffer offer) {
		daoTools.update(offer);
	}

	public GOffer find(Long id) {
		return daoTools.find(GOffer.class, id);
	}

	public QueryResult<GOffer> findAlls() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOffer.class, null,null, 0, 100000, lhm);
	}

	public QueryResult<GOffer> find(LinkedHashMap<String, String> colvals) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOffer.class, colvals, 0, 100000, lhm);
	}

	public QueryResult<GOffer> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOffer.class, null,null, firstindex, 100, lhm);
	}
	
	public QueryResult<GOffer> findAllsByPriority() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("priority", "desc");
		return daoTools.find(GOffer.class, null,null, 0, 100000, lhm);
	}

}
