package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user",
uniqueConstraints={@UniqueConstraint(columnNames = {"name","password"})})
//alter table user add constraint name UNIQUE(name,password);
//alter table user drop index name;
public class GUser {
	private long id;
	private String name;
	private String password;
	private Date createdDate = new Date();
	private Date updatedDate = new Date();
	private String onlineTime = "0";
	private String lastOnlineTime = "0";
	// 设备相关
	private String deviceId;// imei
	private String phoneNumber;// 手机号码
	private String networkOperatorName;// 运营商名称
	private String simSerialNumber;// sim卡序列号
	private String networkCountryIso;// sim卡所在国家
	private String networkOperator;// 运营商编号
	private String networkType;// 网络类型
	private String location;// 移动终端的位置
	/**
	 * 移动终端的类型 PHONE_TYPE_CDMA 手机制式为CDMA，电信 2 PHONE_TYPE_GSM 手机制式为GSM，移动和联通 1
	 * PHONE_TYPE_NONE 手机制式未知 0
	 */
	private int phoneType;//
	private String model;// 手机型号
	private String release;// 系统版本
	private String province;// 省份
	private String city;// 城市
	private String district;// 区县
	private String street;// 街道
	
	private Date spotDate;//插屏时间
	private String spotAdId;//插屏广告id
	private Date openSpotDate;//开屏时间
	private String openSpotAdId;//开屏广告id
	private Date pushDate;//push时间
	private String pushAdId;//push广告id
	
	private String channel;//渠道
	private String trueRelease;// 真系统版本
	private String memory;//内存
	private String storage;//储存
	
	private boolean online;//是否在线
	
	private Integer startUpNum;//自启次数
	private Boolean unInstall;//是否卸载
	
	public GUser() {
	}

	public GUser(String name, String password, String deviceId,
			String phoneNumber, String networkOperatorName,
			String simSerialNumber, String networkCountryIso,
			String networkOperator, String networkType, String location,
			int phoneType, String model, String release) {
		super();
		this.name = name;
		this.password = password;
		this.deviceId = deviceId;
		this.phoneNumber = phoneNumber;
		this.networkOperatorName = networkOperatorName;
		this.simSerialNumber = simSerialNumber;
		this.networkCountryIso = networkCountryIso;
		this.networkOperator = networkOperator;
		this.networkType = networkType;
		this.location = location;
		this.phoneType = phoneType;
		this.model = model;
		this.release = release;
		this.channel = "";

		this.updatedDate = new Date();
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false, length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "created_date", updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(length = 64)
	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	@Column(name = "lastOnlineTime", length = 64)
	public String getLastOnlineTime() {
		return lastOnlineTime;
	}

	public void setLastOnlineTime(String lastOnlineTime) {
		this.lastOnlineTime = lastOnlineTime;
	}

	@Column(name = "deviceId", length = 64)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "phoneNumber", length = 64)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "networkOperatorName", length = 64)
	public String getNetworkOperatorName() {
		return networkOperatorName;
	}

	public void setNetworkOperatorName(String networkOperatorName) {
		this.networkOperatorName = networkOperatorName;
	}

	@Column(name = "simSerialNumber", length = 64)
	public String getSimSerialNumber() {
		return simSerialNumber;
	}

	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}

	@Column(name = "networkCountryIso", length = 64)
	public String getNetworkCountryIso() {
		return networkCountryIso;
	}

	public void setNetworkCountryIso(String networkCountryIso) {
		this.networkCountryIso = networkCountryIso;
	}

	@Column(name = "networkOperator", length = 64)
	public String getNetworkOperator() {
		return networkOperator;
	}

	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}

	@Column(name = "networkType", length = 12)
	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	@Column(name = "location", length = 64)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}

	@Column(name = "model", length = 64)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "sys_release", length = 64)
	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	@Column(name = "province", length = 32)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 32)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "district", length = 32)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "street", length = 64)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Date getSpotDate() {
		return spotDate;
	}

	public void setSpotDate(Date spotDate) {
		this.spotDate = spotDate;
	}
	@Column(length = 128)
	public String getSpotAdId() {
		return spotAdId;
	}

	public void setSpotAdId(String spotAdId) {
		this.spotAdId = spotAdId;
	}

	public Date getOpenSpotDate() {
		return openSpotDate;
	}

	public void setOpenSpotDate(Date openSpotDate) {
		this.openSpotDate = openSpotDate;
	}
	@Column(length = 128)
	public String getOpenSpotAdId() {
		return openSpotAdId;
	}

	public void setOpenSpotAdId(String openSpotAdId) {
		this.openSpotAdId = openSpotAdId;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}
	@Column(length = 128)
	public String getPushAdId() {
		return pushAdId;
	}

	public void setPushAdId(String pushAdId) {
		this.pushAdId = pushAdId;
	}
	
	
	@Column(length = 16)
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	@Column(length = 16)
	public String getTrueRelease() {
		return trueRelease;
	}

	public void setTrueRelease(String trueRelease) {
		this.trueRelease = trueRelease;
	}
	@Column(length = 16)
	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}
	@Column(length = 16)
	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	

	public Integer getStartUpNum() {
		return startUpNum;
	}

	public void setStartUpNum(Integer startUpNum) {
		this.startUpNum = startUpNum;
	}

	public Boolean getUnInstall() {
		return unInstall;
	}

	public void setUnInstall(Boolean unInstall) {
		this.unInstall = unInstall;
	}

	@Transient
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public String toString() {
		return "GUser [id=" + id + ", name=" + name + ", password=" + password
				+ ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", onlineTime=" + onlineTime
				+ ", lastOnlineTime=" + lastOnlineTime 
				+ ", deviceId=" + deviceId + ", phoneNumber=" + phoneNumber
				+ ", networkOperatorName=" + networkOperatorName
				+ ", simSerialNumber=" + simSerialNumber
				+ ", networkCountryIso=" + networkCountryIso
				+ ", networkOperator=" + networkOperator + ", networkType="
				+ networkType + ", location=" + location + ", phoneType="
				+ phoneType + ", model=" + model + ", release=" + release
				+ ", province=" + province + ", city=" + city + ", district="
				+ district + ", street=" + street + "]";
	}
	
	
}
