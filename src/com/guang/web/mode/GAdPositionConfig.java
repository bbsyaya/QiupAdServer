package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ad_positionConfig")
public class GAdPositionConfig {
	private Long id;
	private Long adPositionId;
	private int adPositionType;
	
	//公有属性
	private String timeSlot;//时间段 
	private Integer showNum;//每天广告展示次数
	private Float showTimeInterval;//广告时间间隔
	private String whiteList;//白名单
	
	//浏览器插屏配置
	private Float browerSpotTwoTime;//二次打开时间
	private Float browerSpotFlow;//流量
	//安装
	
	//卸载
	
	//banner
	private Float bannerDelyTime;//banner延迟时间
	
	//充电
	
	//应用插屏
	
	//wifi
	
	//浏览器劫持
	
	//快捷方式
	private String shortcutIconPath;//快捷方式图标路径
	private String shortcutName;//图标名称
	private String shortcutUrl;//链接
	
	//暗刷
	private String behindBrushUrls;

	public GAdPositionConfig(){}
	public GAdPositionConfig(Long adPositionId, String timeSlot,
			Integer showNum, Float showTimeInterval, String whiteList,
			Float browerSpotTwoTime,Float browerSpotFlow, Float bannerDelyTime,
			String behindBrushUrls) {
		super();
		this.adPositionId = adPositionId;
		this.timeSlot = timeSlot;
		this.showNum = showNum;
		this.showTimeInterval = showTimeInterval;
		this.whiteList = whiteList;
		this.browerSpotTwoTime = browerSpotTwoTime;
		this.browerSpotFlow = browerSpotFlow;
		this.bannerDelyTime = bannerDelyTime;
		this.behindBrushUrls = behindBrushUrls;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAdPositionId() {
		return adPositionId;
	}
	public void setAdPositionId(Long adPositionId) {
		this.adPositionId = adPositionId;
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
	public Float getShowTimeInterval() {
		return showTimeInterval;
	}
	public void setShowTimeInterval(Float showTimeInterval) {
		this.showTimeInterval = showTimeInterval;
	}
	@Column(length = 10240)
	public String getWhiteList() {
		return whiteList;
	}
	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}
	public Float getBrowerSpotTwoTime() {
		return browerSpotTwoTime;
	}
	public void setBrowerSpotTwoTime(Float browerSpotTwoTime) {
		this.browerSpotTwoTime = browerSpotTwoTime;
	}
	public Float getBannerDelyTime() {
		return bannerDelyTime;
	}
	public void setBannerDelyTime(Float bannerDelyTime) {
		this.bannerDelyTime = bannerDelyTime;
	}
	@Column(length = 1024)
	public String getBehindBrushUrls() {
		return behindBrushUrls;
	}
	public void setBehindBrushUrls(String behindBrushUrls) {
		this.behindBrushUrls = behindBrushUrls;
	}
	@Column(length = 64)
	public String getShortcutIconPath() {
		return shortcutIconPath;
	}
	public void setShortcutIconPath(String shortcutIconPath) {
		this.shortcutIconPath = shortcutIconPath;
	}
	@Column(length = 64)
	public String getShortcutName() {
		return shortcutName;
	}
	public void setShortcutName(String shortcutName) {
		this.shortcutName = shortcutName;
	}
	@Column(length = 128)
	public String getShortcutUrl() {
		return shortcutUrl;
	}
	public void setShortcutUrl(String shortcutUrl) {
		this.shortcutUrl = shortcutUrl;
	}
	@Transient
	public int getAdPositionType() {
		return adPositionType;
	}
	public void setAdPositionType(int adPositionType) {
		this.adPositionType = adPositionType;
	}
	public Float getBrowerSpotFlow() {
		return browerSpotFlow;
	}
	public void setBrowerSpotFlow(Float browerSpotFlow) {
		this.browerSpotFlow = browerSpotFlow;
	}
	
	
}
