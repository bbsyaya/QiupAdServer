package com.guang.web.mode;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "gsdk")
public class GSdk {
	private Long id;
	private String packageName;// 包名
	private String versionName;// 版本名
	private String versionCode;// 版本号
	private String downloadPath;//下载路径 
	private Boolean online;//是否上线
	private Long updateNum;//更新次数
	private String channel;//渠道
	private Date updatedDate;
	
	private String netTypes;//网络
	private String sdkType;//sdk类型
	
	private String name;//应用名字
	private String appPackageName;// 应用包名
	private String adPosition;
	private Float loopTime;
	private Boolean uploadPackage;//是否上传包名
	
	private String province;//省份
	
	private String adPositionName;
	private List<GAdPositionConfig> configs;
	
	public GSdk(){}
	public GSdk(String packageName, String versionName,String versionCode, String downloadPath,
			Boolean online,Long updateNum,String channel) {
		super();
		this.packageName = packageName;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.downloadPath = downloadPath;
		this.online = online;
		this.updateNum = updateNum;
		this.channel = channel;
		this.updatedDate = new Date();
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "packageName", length = 128)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Column(name = "versionName", length = 64)
	public String getVersionName() {
		return versionName;
	}
	@Column(name = "versionCode", length = 64)
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	@Column(name = "downloadPath",  length = 128) 
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public Boolean getOnline() {
		return online;
	}
	public void setOnline(Boolean online) {
		this.online = online;
	}
	public Long getUpdateNum() {
		return updateNum;
	}
	public void setUpdateNum(Long updateNum) {
		this.updateNum = updateNum;
	}
	@Column(name = "channel", length = 64)
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Column(name = "netTypes",  length = 128) 
	public String getNetTypes() {
		return netTypes;
	}
	public void setNetTypes(String netTypes) {
		this.netTypes = netTypes;
	}
	@Column(name = "sdkType",  length = 64) 
	public String getSdkType() {
		return sdkType;
	}
	public void setSdkType(String sdkType) {
		this.sdkType = sdkType;
	}
	@Column(length = 64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 64)
	public String getAppPackageName() {
		return appPackageName;
	}
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}
	@Column(length = 128)
	public String getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}
	public Float getLoopTime() {
		return loopTime;
	}
	public void setLoopTime(Float loopTime) {
		this.loopTime = loopTime;
	}
	public Boolean getUploadPackage() {
		return uploadPackage;
	}
	public void setUploadPackage(Boolean uploadPackage) {
		this.uploadPackage = uploadPackage;
	}
	
	@Column(length = 1024)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Transient
	public String getAdPositionName() {
		return adPositionName;
	}

	public void setAdPositionName(String adPositionName) {
		this.adPositionName = adPositionName;
	}
	@Transient
	public List<GAdPositionConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<GAdPositionConfig> configs) {
		this.configs = configs;
	}
	
	
	
}
