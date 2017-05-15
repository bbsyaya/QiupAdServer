package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "g_offer")
public class GOffer {
	private Long id;
	private String appName;
	private String packageName;
	private String appDesc;
	private String picPath;
	private String iconPath;
	private String apkPath;
	private float apkSize;
	private int priority;//优先级
	private String areas;
	private Date updatedDate;
	
	private String channels;
	private String adPositions;
	private String channelNames;
	private String operators;//运营商
	
	private Float pice;
	
	public GOffer(){}
	public GOffer(String appName, String packageName, String appDesc,
			String picPath, String iconPath, String apkPath, float apkSize,
			int priority, String areas,String channels,String adPositions,
			String channelNames) {
		super();
		this.appName = appName;
		this.packageName = packageName;
		this.appDesc = appDesc;
		this.picPath = picPath;
		this.iconPath = iconPath;
		this.apkPath = apkPath;
		this.apkSize = apkSize;
		this.priority = priority;
		this.areas = areas;
		this.updatedDate = new Date();
		
		this.channels = channels;
		this.adPositions = adPositions;
		this.channelNames = channelNames;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(length = 128)
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	@Column(name = "packageName", length = 128)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Column(length = 512)
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	@Column(length = 128)
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Column(length = 128)
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	@Column(length = 256)
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
	public float getApkSize() {
		return apkSize;
	}
	public void setApkSize(float apkSize) {
		this.apkSize = apkSize;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Lob
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Lob
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	@Lob
	public String getAdPositions() {
		return adPositions;
	}
	public void setAdPositions(String adPositions) {
		this.adPositions = adPositions;
	}
	@Lob
	public String getChannelNames() {
		return channelNames;
	}
	public void setChannelNames(String channelNames) {
		this.channelNames = channelNames;
	}
	public Float getPice() {
		return pice;
	}
	public void setPice(Float pice) {
		this.pice = pice;
	}
	@Column(length = 16)
	public String getOperators() {
		return operators;
	}
	public void setOperators(String operators) {
		this.operators = operators;
	}
	
	
}
