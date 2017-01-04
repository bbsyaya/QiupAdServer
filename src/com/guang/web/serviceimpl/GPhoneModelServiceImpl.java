package com.guang.web.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GPhoneModel;
import com.guang.web.service.GPhoneModelService;


@Service
public class GPhoneModelServiceImpl implements GPhoneModelService {
	@Resource private DaoTools daoTools;

	public void add(GPhoneModel phoneModel) {
		try {
			if(find(phoneModel.getModel()) == null)
				daoTools.add(phoneModel);
		} catch (Exception e) {
			
		}
	}

	public GPhoneModel find(int id) {
		return daoTools.find(GPhoneModel.class, id);
	}

	public GPhoneModel find(String model) {
		List<GPhoneModel> list = daoTools.find(GPhoneModel.class, "model", model, 0, 1, null).getList();
		if(list != null && list.size() > 0)
		return list.get(0);
		return null;
	}

	public QueryResult<GPhoneModel> findAll() {
		return daoTools.find(GPhoneModel.class, null, null, 0, 100000, null);
	}

}
