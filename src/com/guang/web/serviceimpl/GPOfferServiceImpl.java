package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GPOffer;
import com.guang.web.service.GPOfferService;
@Service
public class GPOfferServiceImpl implements GPOfferService{
	@Resource private DaoTools daoTools;
	public void add(GPOffer offer) {
		daoTools.add(offer);
	}

	public void delete(Long id) {
		daoTools.delete(GPOffer.class, id);
	}

	public void update(GPOffer offer) {
		daoTools.update(offer);
	}

	public GPOffer find(Long id) {
		return daoTools.find(GPOffer.class, id);
	}
	public QueryResult<GPOffer> findAlls() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GPOffer.class, null,null, 0, 100000, lhm);
	}

	public QueryResult<GPOffer> find(LinkedHashMap<String, String> colvals) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GPOffer.class, colvals, 0, 100000, lhm);
	}

	public QueryResult<GPOffer> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GPOffer.class, null,null, firstindex, 100, lhm);
	}
	public QueryResult<GPOffer> findAllsByPriority() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("priority", "desc");
		return daoTools.find(GPOffer.class, null,null, 0, 100000, lhm);
	}

}
