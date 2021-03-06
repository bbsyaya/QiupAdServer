package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GFilterApp;
import com.guang.web.mode.GSdk;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GFilterAppService;
import com.guang.web.service.GSdkService;
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
			
			
			GSdk sdks = new GSdk(packageName, versionName, versionCode, downloadPath, online,0l,channel);
			sdks.setNetTypes(netTypes);
			sdks.setSdkType(sdkType);
			sdks.setName(name);
			sdks.setAppPackageName(appPackageName);
			sdks.setUploadPackage(uploadPackage);
			sdks.setAdPosition(adPositionSwitch);
			if(loopTime != null && !"".equals(loopTime))
			{
				sdks.setLoopTime(Float.parseFloat(loopTime));
			}
			
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
			
			
			sdk.setNetTypes(netTypes);
			sdk.setSdkType(sdkType);
			sdk.setName(name);
			sdk.setAppPackageName(appPackageName);
			sdk.setUploadPackage(uploadPackage);
			sdk.setAdPosition(adPositionSwitch);
			if(loopTime != null && !"".equals(loopTime))
			{
				sdk.setLoopTime(Float.parseFloat(loopTime));
			}
			
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
			GSdk sdk = sdkService.findNew(channel);
			print(JSONObject.fromObject(sdk).toString());
		}
		else
		{
			channel = ServletActionContext.getRequest().getParameter("channel");
			String packageName = ServletActionContext.getRequest().getParameter("packageName");
			if(channel != null && packageName != null)
			{
				GSdk sdk = sdkService.findNew2(packageName,channel);
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
			GSdk sdk = sdkService.findNew(channel);
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
			if(channel != null && packageName != null)
			{
				GSdk sdk = sdkService.findNew2(packageName,channel);
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
