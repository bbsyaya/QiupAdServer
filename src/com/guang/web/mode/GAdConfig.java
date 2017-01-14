package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ad_config")
public class GAdConfig {
	private Long id;
	private String name;//配置方案名称
	private Boolean open;//是否打开
	private String whiteList;//白名单
	private String timeSlot;//时间段 
	private Integer showNum;//每天广告展示次数
	private Integer repeatNum;//每天广告重复次数
	private Float showTimeInterval;//广告时间间隔
	private String appSwitch;//媒体开关
	private String adPositionSwitch;//广告位开关
	private String browerWhiteList;//浏览器白名单
	
	public GAdConfig(){}

	public GAdConfig(String name, Boolean open, String whiteList,
			String timeSlot, Integer showNum, Integer repeatNum,Float showTimeInterval,
			String browerWhiteList) {
		super();
		this.name = name;
		this.open = open;
		this.whiteList = whiteList;
		this.timeSlot = timeSlot;
		this.showNum = showNum;
		this.repeatNum = repeatNum;
		this.showTimeInterval = showTimeInterval;
		this.browerWhiteList = browerWhiteList;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	@Column(length = 10240)
	public String getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}
	@Column(length = 512)
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Integer getRepeatNum() {
		return repeatNum;
	}

	public void setRepeatNum(Integer repeatNum) {
		this.repeatNum = repeatNum;
	}

	public Float getShowTimeInterval() {
		return showTimeInterval;
	}

	public void setShowTimeInterval(Float showTimeInterval) {
		this.showTimeInterval = showTimeInterval;
	}
	@Transient
	public String getAppSwitch() {
		return appSwitch;
	}

	public void setAppSwitch(String appSwitch) {
		this.appSwitch = appSwitch;
	}
	@Transient
	public String getAdPositionSwitch() {
		return adPositionSwitch;
	}

	public void setAdPositionSwitch(String adPositionSwitch) {
		this.adPositionSwitch = adPositionSwitch;
	}
	@Column(length = 10240)
	public String getBrowerWhiteList() {
		return browerWhiteList;
	}

	public void setBrowerWhiteList(String browerWhiteList) {
		this.browerWhiteList = browerWhiteList;
	}
	
}
