package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gather_runinfo")
public class GatherAppRunInfo {
	private long id;
	private String deviceId;
	private Date gdate;
	private String packageName;
	private String appName;
	private String clazName;
	private boolean inlay;
	private Date startTime;
	private long useTime;
	private boolean wifi;
	
	private String useTimes;
	
	
	public GatherAppRunInfo() {
	}


	public GatherAppRunInfo(String deviceId, String packageName,
			String appName,String clazName, boolean inlay, Date startTime, long useTime,
			boolean wifi) {
		this.deviceId = deviceId;
		this.packageName = packageName;
		this.appName = appName;
		this.clazName = clazName;
		this.inlay = inlay;
		this.startTime = startTime;
		this.useTime = useTime;
		this.wifi = wifi;
		this.gdate = new Date();
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	@Column(length = 64)
	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public Date getGdate() {
		return gdate;
	}


	public void setGdate(Date gdate) {
		this.gdate = gdate;
	}

	@Column(length = 64)
	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(length = 64)
	public String getAppName() {
		return appName;
	}


	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(length =128)
	public String getClazName() {
		return clazName;
	}


	public void setClazName(String clazName) {
		this.clazName = clazName;
	}

	public boolean isInlay() {
		return inlay;
	}


	


	public void setInlay(boolean inlay) {
		this.inlay = inlay;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public long getUseTime() {
		return useTime;
	}


	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}


	

	public boolean isWifi() {
		return wifi;
	}


	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}


	@Transient
	public String getUseTimes() {
		return useTimes;
	}


	public void setUseTimes(String useTimes) {
		this.useTimes = useTimes;
	}


	
}
