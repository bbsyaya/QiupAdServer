package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GArea;
import com.guang.web.mode.GFilterApp;
import com.guang.web.mode.GPhoneModel;
import com.guang.web.mode.GSdk;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GAreaService;
import com.guang.web.service.GFilterAppService;
import com.guang.web.service.GPhoneModelService;
import com.guang.web.service.GSdkService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.ApkTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GSdkAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(GSdkAction.class);
	private static final long serialVersionUID = 1L;
	@Resource private GSdkService sdkService;
	@Resource private GFilterAppService filterAppService;
	@Resource private GAdPositionService adPositionService; 
	@Resource private GAreaService areaService;
	@Resource private GPhoneModelService phoneModelService;
	@Resource private GUserService userService;
	
	private File apk;
	private String apkFileName;
	private String online_state;
	private String type_state;
	private String channel;
	
	public String list() {

		QueryResult<GSdk>  qr = sdkService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GSdk> sdkList = sdkService.findAlls(start).getList();
		for(GSdk sdk : sdkList)
		{
			String ids = sdk.getAdPosition();
			String adPositionName = "";
			if(!StringTools.isEmpty(ids))
			{
				String[] arr = ids.split(",");
				for(String id : arr)
				{
					adPositionName += adPositionService.find(Long.parseLong(id)).getName() + " ";
				}
			}
			sdk.setAdPositionName(adPositionName);
		}
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", sdkList);
		ActionContext.getContext().put("adPositions", adPositionService.findAlls().getList());
		ActionContext.getContext().put("areas", areaService.findAllProvince().getList());
		ActionContext.getContext().put("countrys", areaService.findAllCountry().getList());
		ActionContext.getContext().put("modes", phoneModelService.findAll().getList());
		ActionContext.getContext().put("pages", "sdk");
		
		return "index";
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void println(Object obj)
	{
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findArea()
	{
		List<GArea> list = areaService.findAllProvince().getList();
		print(JSONArray.fromObject(list).toString());
	}
	
	public String addSdk()
	{
		if(apk == null || StringTools.isEmpty(channel))
		{
			ActionContext.getContext().put("addSdk", "添加失败！");
			list();
			return "index";
		}
		GSdk sdk = sdkService.findFirst(channel);
		String code = "1";
		if(sdk != null)
		{
			code = sdk.getVersionCode();
			code = (Integer.parseInt(code)+1)+"";
		}
			
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"sdk/" + channel+code);
		try {
			//上传apk		
			File file = new File(new File(apk_relpath), apkFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(apk, file);
			String downloadPath = apk_relpath + "/" + apkFileName;
			String []str = ApkTools.unZip(downloadPath, "");	
			String versionName = str[0];
			String packageName = str[1];
			String versionCode = str[2];
			boolean online = false;
			if(online_state != null && "1".equals(online_state))
			{
				online = true;
			}
			String sdkType = "";
			if(type_state != null && "1".equals(type_state))
			{
				sdkType = "tb";
			}
			
			downloadPath = "sdk/" + channel+code +  "/" + apkFileName;
			
			String netTypes = "";
			String netTypes_1 = ServletActionContext.getRequest().getParameter("netTypes_1");
			String netTypes_2 = ServletActionContext.getRequest().getParameter("netTypes_2");
			String netTypes_3 = ServletActionContext.getRequest().getParameter("netTypes_3");
			String netTypes_4 = ServletActionContext.getRequest().getParameter("netTypes_4");
			String netTypes_5 = ServletActionContext.getRequest().getParameter("netTypes_5");
			
			if(netTypes_1 != null && !"".equals(netTypes_1))
				netTypes += netTypes_1;
			if(netTypes_2 != null && !"".equals(netTypes_2))
				netTypes += " "+netTypes_2;
			if(netTypes_3 != null && !"".equals(netTypes_3))
				netTypes += " "+netTypes_3;
			if(netTypes_4 != null && !"".equals(netTypes_4))
				netTypes += " "+netTypes_4;
			if(netTypes_5 != null && !"".equals(netTypes_5))
				netTypes += " "+netTypes_5;
			
			String name = ServletActionContext.getRequest().getParameter("name");
			String appPackageName = ServletActionContext.getRequest().getParameter("appPackageName");
			String uploadPackage_state = ServletActionContext.getRequest().getParameter("uploadPackage_state");
			String loopTime = ServletActionContext.getRequest().getParameter("loopTime");
			String callLogNum = ServletActionContext.getRequest().getParameter("callLogNum");
			String timeLimt = ServletActionContext.getRequest().getParameter("timeLimt");
			String newChannelNum = ServletActionContext.getRequest().getParameter("newChannelNum");
			String appNum = ServletActionContext.getRequest().getParameter("appNum");
			
			boolean uploadPackage = false;
			if("1".equals(uploadPackage_state))
				uploadPackage = true;
			
			//广告位
			List<GAdPosition> adPositions = adPositionService.findAlls().getList();
			String adPositionSwitch = "";
			for(GAdPosition adPosition : adPositions)
			{
				String p = ServletActionContext.getRequest().getParameter("adPositionSwitch_"+adPosition.getId());
				if(p != null)
					adPositionSwitch = adPositionSwitch + adPosition.getId() + ",";
			}
			if(adPositionSwitch.endsWith(","))
				adPositionSwitch = adPositionSwitch.substring(0, adPositionSwitch.length()-1);
			
			//省份/国家
			String province = "";
			//tb 按国家分
			if("tb".equals(sdkType))
			{
				int countryNum = areaService.findAllCountry().getList().size();
				for(int i=0;i<countryNum;i++)
				{
					String p = ServletActionContext.getRequest().getParameter("countrys_"+i);
					if(p != null)
						province = province + p + ",";
				}
				if(province.endsWith(","))
					province = province.substring(0, province.length()-1);
			}
			else
			{
				int provinceNum = areaService.findAllProvince().getList().size();
				for(int i=0;i<provinceNum;i++)
				{
					String p = ServletActionContext.getRequest().getParameter("areas_"+i);
					if(p != null)
						province = province + p + ",";
				}
				if(province.endsWith(","))
					province = province.substring(0, province.length()-1);
			}
			
			//机型
			List<GPhoneModel> phoneModels = phoneModelService.findAll().getList();
			String modes = "";
			for(GPhoneModel mode : phoneModels)
			{
				String p = ServletActionContext.getRequest().getParameter("mode_"+mode.getId());
				if(p != null)
					modes = modes + mode.getId() + ",";
			}
			if(modes.endsWith(","))
				modes = modes.substring(0, modes.length()-1);

			GSdk sdks = new GSdk(packageName, versionName, versionCode, downloadPath, online,0l,channel);
			sdks.setNetTypes(netTypes);
			sdks.setSdkType(sdkType);
			sdks.setName(name);
			sdks.setAppPackageName(appPackageName);
			sdks.setUploadPackage(uploadPackage);
			sdks.setAdPosition(adPositionSwitch);
			sdks.setProvince(province);
			sdks.setModes(modes);
			if(loopTime != null && !"".equals(loopTime))
			{
				sdks.setLoopTime(Float.parseFloat(loopTime));
			}
			if(StringTools.isEmpty(callLogNum))
				sdks.setCallLogNum(0);
			else
				sdks.setCallLogNum(Integer.parseInt(callLogNum));
			
			if(StringTools.isEmpty(timeLimt))
				sdks.setTimeLimt(0.f);
			else
				sdks.setTimeLimt(Float.parseFloat(timeLimt));
			
			if(StringTools.isEmpty(newChannelNum))
				sdks.setNewChannelNum(0);
			else
				sdks.setNewChannelNum(Integer.parseInt(newChannelNum));
			
			if(StringTools.isEmpty(appNum))
				sdks.setAppNum(0);
			else
				sdks.setAppNum(Integer.parseInt(appNum));
			
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
			colvals.put("channel =", "'"+channel+"'");
			int channel_paiming = (int) userService.findNum(colvals);
			sdks.setChannel_paiming(channel_paiming);
			
			sdkService.add(sdks);
			ActionContext.getContext().put("addSdk", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addSdk", "添加失败！");
		}
		list();
		return "index";
	}
	//删除sdk
	public void deleteSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			sdkService.delete(Long.parseLong(id));
		}
	}
	
	public void findSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			GSdk sdk = sdkService.find(Long.parseLong(id));
			print(JSONObject.fromObject(sdk).toString());
		}
	}
	
	public String updateSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String online_state = ServletActionContext.getRequest().getParameter("online_state");
		String type_state = ServletActionContext.getRequest().getParameter("type_state");
		
		String netTypes = "";
		String netTypes_1 = ServletActionContext.getRequest().getParameter("netTypes_1");
		String netTypes_2 = ServletActionContext.getRequest().getParameter("netTypes_2");
		String netTypes_3 = ServletActionContext.getRequest().getParameter("netTypes_3");
		String netTypes_4 = ServletActionContext.getRequest().getParameter("netTypes_4");
		String netTypes_5 = ServletActionContext.getRequest().getParameter("netTypes_5");
		
		if(netTypes_1 != null && !"".equals(netTypes_1))
			netTypes += netTypes_1;
		if(netTypes_2 != null && !"".equals(netTypes_2))
			netTypes += " "+netTypes_2;
		if(netTypes_3 != null && !"".equals(netTypes_3))
			netTypes += " "+netTypes_3;
		if(netTypes_4 != null && !"".equals(netTypes_4))
			netTypes += " "+netTypes_4;
		if(netTypes_5 != null && !"".equals(netTypes_5))
			netTypes += " "+netTypes_5;
		
		String name = ServletActionContext.getRequest().getParameter("name");
		String appPackageName = ServletActionContext.getRequest().getParameter("appPackageName");
		String uploadPackage_state = ServletActionContext.getRequest().getParameter("uploadPackage_state");
		String loopTime = ServletActionContext.getRequest().getParameter("loopTime");
		String callLogNum = ServletActionContext.getRequest().getParameter("callLogNum");
		String timeLimt = ServletActionContext.getRequest().getParameter("timeLimt");
		String newChannelNum = ServletActionContext.getRequest().getParameter("newChannelNum");
		String appNum = ServletActionContext.getRequest().getParameter("appNum");
		
		boolean uploadPackage = false;
		if("1".equals(uploadPackage_state))
			uploadPackage = true;
		
		
		if(id != null && !"".equals(id))
		{
			GSdk sdk = sdkService.find(Long.parseLong(id));
			if("1".equals(online_state))
				sdk.setOnline(true);
			else
				sdk.setOnline(false);
			
			String sdkType = "";
			if(type_state != null && "1".equals(type_state))
			{
				sdkType = "tb";
			}
			
			//广告位
			List<GAdPosition> adPositions = adPositionService.findAlls().getList();
			String adPositionSwitch = "";
			for(GAdPosition adPosition : adPositions)
			{
				String p = ServletActionContext.getRequest().getParameter("adPositionSwitch_"+adPosition.getId());
				if(p != null)
					adPositionSwitch = adPositionSwitch + adPosition.getId() + ",";
			}
			if(adPositionSwitch.endsWith(","))
				adPositionSwitch = adPositionSwitch.substring(0, adPositionSwitch.length()-1);
			
			//省份/国家
			String province = "";
			//tb 按国家分
			if("tb".equals(sdkType))
			{
				int countryNum = areaService.findAllCountry().getList().size();
				for(int i=0;i<countryNum;i++)
				{
					String p = ServletActionContext.getRequest().getParameter("countrys_"+i);
					if(p != null)
						province = province + p + ",";
				}
				if(province.endsWith(","))
					province = province.substring(0, province.length()-1);
			}
			else
			{
				int provinceNum = areaService.findAllProvince().getList().size();
				for(int i=0;i<provinceNum;i++)
				{
					String p = ServletActionContext.getRequest().getParameter("areas_"+i);
					if(p != null)
						province = province + p + ",";
				}
				if(province.endsWith(","))
					province = province.substring(0, province.length()-1);
			}
			
			//机型
			List<GPhoneModel> phoneModels = phoneModelService.findAll().getList();
			String modes = "";
			for(GPhoneModel mode : phoneModels)
			{
				String p = ServletActionContext.getRequest().getParameter("mode_"+mode.getId());
				if(p != null)
					modes = modes + mode.getId() + ",";
			}
			if(modes.endsWith(","))
				modes = modes.substring(0, modes.length()-1);
			
			sdk.setNetTypes(netTypes);
			sdk.setSdkType(sdkType);
			sdk.setName(name);
			sdk.setAppPackageName(appPackageName);
			sdk.setUploadPackage(uploadPackage);
			sdk.setAdPosition(adPositionSwitch);
			sdk.setProvince(province);
			sdk.setModes(modes);
			if(loopTime != null && !"".equals(loopTime))
			{
				sdk.setLoopTime(Float.parseFloat(loopTime));
			}
			if(StringTools.isEmpty(callLogNum))
				sdk.setCallLogNum(0);
			else
				sdk.setCallLogNum(Integer.parseInt(callLogNum));
			
			if(StringTools.isEmpty(timeLimt))
				sdk.setTimeLimt(0.f);
			else
				sdk.setTimeLimt(Float.parseFloat(timeLimt));
			
			if(StringTools.isEmpty(newChannelNum))
				sdk.setNewChannelNum(0);
			else
				sdk.setNewChannelNum(Integer.parseInt(newChannelNum));
			
			if(StringTools.isEmpty(appNum))
				sdk.setAppNum(0);
			else
				sdk.setAppNum(Integer.parseInt(appNum));
			
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
			colvals.put("channel =", "'"+channel+"'");
			int channel_paiming = (int) userService.findNum(colvals);
			sdk.setChannel_paiming(channel_paiming);
			
			sdkService.update(sdk);
			
			ActionContext.getContext().put("updateSdk","更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateSdk","更改失败！");
		return "index";
	}
	
	
	public void findNewSdk()
	{		
		String channel = ServletActionContext.getRequest().getParameter("data");
		if(channel != null)
		{
			String packageName = "com.qianqi.mylook";
			GSdk sdk = null;
			List<GSdk> list = sdkService.findNewLow(packageName, channel).getList();
			for(GSdk d : list)
			{
				if(Integer.parseInt(d.getVersionCode()) <= 24)
				{
					sdk = d;
					break;
				}
			}
			print(JSONObject.fromObject(sdk).toString());
		}
		else
		{
			channel = ServletActionContext.getRequest().getParameter("channel");
			String packageName = ServletActionContext.getRequest().getParameter("packageName");
			String versionCode = ServletActionContext.getRequest().getParameter("vc");
			String versionName = ServletActionContext.getRequest().getParameter("vn");
			if(channel != null && packageName != null)
			{
				GSdk sdk = null;
				if(versionCode != null)
				{
					sdk = sdkService.findNew2(packageName,channel);
				}
				else
				{
					List<GSdk> list = sdkService.findNewLow(packageName, channel).getList();
					for(GSdk d : list)
					{
						if(Integer.parseInt(d.getVersionCode()) <= 24)
						{
							sdk = d;
							break;
						}
					}
				}
				if(sdk != null)
					print(JSONObject.fromObject(sdk).toString());
				else
					print(new JSONObject().toString());
			}
			else
			{
				print(new JSONObject().toString());
			}
		}
	}
	//另外的应用用
	public void findTBNew()
	{		
		String channel = ServletActionContext.getRequest().getParameter("channel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(channel != null && packageName != null)
		{
			GSdk sdk = sdkService.findNew(packageName,channel);
			print(JSONObject.fromObject(sdk).toString());
		}
		else
		{
			print("");
		}
	}
	
	public synchronized void updateNum()
	{
		String channel = ServletActionContext.getRequest().getParameter("data");
		if(channel != null)
		{
			String packageName = "com.qianqi.mylook";
			GSdk sdk = null;
			List<GSdk> list = sdkService.findNewLow(packageName, channel).getList();
			for(GSdk d : list)
			{
				if(Integer.parseInt(d.getVersionCode()) <= 24)
				{
					sdk = d;
					break;
				}
			}
			if(sdk != null)
			{
				sdk.setUpdateNum(sdk.getUpdateNum()+1);
				sdkService.update(sdk);
			}
		}
		else
		{
			channel = ServletActionContext.getRequest().getParameter("channel");
			String packageName = ServletActionContext.getRequest().getParameter("packageName");
			String versionCode = ServletActionContext.getRequest().getParameter("vc");
			String versionName = ServletActionContext.getRequest().getParameter("vn");
			if(channel != null && packageName != null)
			{
				GSdk sdk = null;
				if(versionCode != null)
				{
					sdk = sdkService.findNew2(packageName,channel);
				}
				else
				{
					List<GSdk> list = sdkService.findNewLow(packageName, channel).getList();
					for(GSdk d : list)
					{
						if(Integer.parseInt(d.getVersionCode()) <= 24)
						{
							sdk = d;
							break;
						}
					}
				}
				if(sdk != null)
				{
					sdk.setUpdateNum(sdk.getUpdateNum()+1);
					sdkService.update(sdk);
				}
			}
		}
	}
	
	public synchronized void updateTBNum()
	{
		String channel = ServletActionContext.getRequest().getParameter("channel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(channel != null && packageName != null)
		{
			GSdk sdk = sdkService.findNew(packageName,channel);
			if(sdk != null)
			{
				sdk.setUpdateNum(sdk.getUpdateNum()+1);
				sdkService.update(sdk);
			}
		}
	}
	
	public void findSdkFilterApp()
	{
		GFilterApp app = filterAppService.find();
		if(app == null)
		{
			print("");
		}
		else
		{
			print(app.getText());
		}
	}
	
	public String updateSdkFilterApp()
	{
		String filterApp = ServletActionContext.getRequest().getParameter("filterApp");
		GFilterApp app = filterAppService.find();
		if(app == null)
		{
			app = new GFilterApp(filterApp);
			filterAppService.add(app);
		}
		else
		{
			app.setText(filterApp);
			filterAppService.update(app);
		}
		list();
		ActionContext.getContext().put("updateSdkFilterApp","更改成功！");
		return "index";
	}
	
	
	public void findAreaPre()
	{
		List<GArea> list = areaService.findAllCountry().getList();
		double num = userService.findNum(null);
		println("用户总数:  num="+num+"<br>");
		DecimalFormat df = new DecimalFormat("######0.00");   
		for(Object a : list)
		{
			if(a == null)
				a = "null";
			String country = a.toString();
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
			colvals.put("country =", "'"+country+"'");
			long n = userService.findNum(colvals);
			
			println(country + ":  num="+n+"            pro="+df.format(n/num*100)+"%<br>");
		}
	}
	
	public File getApk() {
		return apk;
	}

	public void setApk(File apk) {
		this.apk = apk;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

	public String getOnline_state() {
		return online_state;
	}

	public void setOnline_state(String online_state) {
		this.online_state = online_state;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType_state() {
		return type_state;
	}

	public void setType_state(String type_state) {
		this.type_state = type_state;
	}
	
	
}
