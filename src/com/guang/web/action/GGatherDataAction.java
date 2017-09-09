package com.guang.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GatherAppInfo;
import com.guang.web.mode.GatherAppRunInfo;
import com.guang.web.service.GGatherDataService;
import com.guang.web.service.RunAppInfoService;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;

public class GGatherDataAction {
	//app上传信息
	@Resource GGatherDataService dataService;
	//app运行
	@Resource RunAppInfoService appInfoService;
	/**
	 * 每天上传的app信息
	 * @return
	 */
	public String list(){
		QueryResult<GatherAppInfo>  qr = dataService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {	
			start = 0;
		}
		List<GatherAppInfo> appInfoList = dataService.findAlls(start).getList();
		ActionContext.getContext().put("appInfoList", appInfoList);
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("pages", "gather");
		
		//app运行信息
		QueryResult<GatherAppRunInfo>  qrg = appInfoService.findAlls(0);
		String rSindex = ServletActionContext.getRequest().getParameter("index");
		int rIndex = 0;
		if (rSindex != null && !"".equals(rSindex))
		rIndex = Integer.parseInt(rSindex);
		Long sNum = qrg.getNum();
		int starts = rIndex * 100;
		if (starts > sNum) {
			starts = 0;
		}
		List<GatherAppRunInfo> runInfoList = appInfoService.findAlls(starts).getList();
		for(GatherAppRunInfo info : runInfoList)
		{
			info.setUseTimes(GTools.time2String(info.getUseTime()));
		}
		ActionContext.getContext().put("runInfoList", runInfoList);
		ActionContext.getContext().put("rmaxNum", sNum);
		ActionContext.getContext().put("pages", "gather");
		return "index";
	}
	
	public String list2(){
		QueryResult<GatherAppInfo>  qr = dataService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {	
			start = 0;
		}
		List<GatherAppInfo> appInfoList = dataService.findAlls(start).getList();
		ActionContext.getContext().put("appInfoList", appInfoList);
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("pages", "gather");
		
		
		//app运行信息
		QueryResult<GatherAppRunInfo>  qrg = appInfoService.findAlls(0);
		String rSindex = ServletActionContext.getRequest().getParameter("index");
		int rIndex = 0;
		if (rSindex != null && !"".equals(rSindex))
			rIndex = Integer.parseInt(rSindex);
		Long sNum = qrg.getNum();
		int starts = rIndex * 20;
		if (starts > sNum) {
			starts = 0;
		}
		List<GatherAppRunInfo> runInfoList = appInfoService.findAlls(starts).getList();
		for(GatherAppRunInfo info : runInfoList)
		{
			info.setUseTimes(GTools.time2String(info.getUseTime()));
		}
		ActionContext.getContext().put("runInfoList", runInfoList);
		ActionContext.getContext().put("rmaxNum", sNum);
		ActionContext.getContext().put("pages", "gather");
		return "index";
	}
	/**
	 *  删除APP上传信息
	 */
	public void deleteAppInfo(){
		String id = ServletActionContext.getRequest().getParameter("id");
		dataService.delete(Integer.parseInt(id));
	}
	
	
	/**
	 *  删除APP运行信息
	 */
	public void deleteRunInfo(){
		String id = ServletActionContext.getRequest().getParameter("id");
		appInfoService.delete(Integer.parseInt(id));
	}
	
	public String find(){
		String regFrom = ServletActionContext.getRequest().getParameter("regDate_from");
		String regTo = ServletActionContext.getRequest().getParameter("regDate_to");
		
		List<GatherAppInfo> gUser = null ; 
		//注册时间
		if (null!=regFrom&&!"".equals(regFrom) && null!=regTo&&!"".equals(regTo)) {
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();			
			colvals.put("gdate >=", "'"+regFrom+"'");
			colvals.put("gdate <", "'"+regTo+"'");
			gUser = dataService.find(colvals).getList();
			long m = dataService.find(colvals).getNum();
			
			ActionContext.getContext().put("appInfoList", gUser);
			ActionContext.getContext().put("maxNum", m);
			ActionContext.getContext().put("pages", "gather");
		}
		
		return "index";
	}
	
	//上传APP上传信息
	public synchronized void uploadAppInfo()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONArray arr = JSONArray.fromObject(data);
		List<GatherAppInfo> list = (List<GatherAppInfo>) JSONArray.toCollection(arr, GatherAppInfo.class);
		for(GatherAppInfo info : list)
		{
			info.setGdate(new Date());
			dataService.add(info);
		}
	}
	
	//上传APP运行信息
	public synchronized void uploadAppRunInfo()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data);
		String deviceId = obj.getString("deviceId");
		String packageName = obj.getString("packageName");
		String appName = obj.getString("appName");		
		long time = obj.getLong("time");
		long use_time = obj.getLong("use_time");
		boolean inlay = obj.getBoolean("inlay");
		boolean isWifi = obj.getBoolean("isWifi");
		
		String clazName = "";
		if(obj.has("clazName"))
			clazName = obj.getString("clazName");
		Date startTime = new Date(time);
		
		GatherAppRunInfo appRunInfo = new GatherAppRunInfo(deviceId, packageName, appName, clazName, inlay, startTime, use_time, isWifi);
		appInfoService.add(appRunInfo);
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void daochu()
	{
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		String inlay = ServletActionContext.getRequest().getParameter("inlay");
		if(!StringTools.isEmpty(from) && !StringTools.isEmpty(from))
		{
			colvals.put("gdate between", "'"+from+"'" + " and " + "'"+to+"'");
		}
		if(!StringTools.isEmpty(inlay) )
		{
			boolean lay = false;
			if("1".equals(inlay))
				lay = true;
			colvals.put("inlay =", lay + "");
		}
		List<GatherAppInfo> appInfoList = dataService.find(colvals).getList();
		for(GatherAppInfo info : appInfoList)
		{
			String s = info.getAppName() + ";" + info.getPackageName() + ";" + info.isInlay() + ";\r\n";
			print(s);
		}
	}
}
