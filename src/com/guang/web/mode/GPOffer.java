package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "gp_offer")
public class GPOffer {
	private long id;
	private String name;// 应用名字
	private String packageName;// 包名
	private String trackUrl;//跟踪链接
	private String gpUrl;
	private int priority;//优先级
	private String adPositions;
	private String channels;
	private String countrys;
	private Date updatedDate;
	
	private String channelNames;
	
	public GPOffer(){}
	public GPOffer(String name, String packageName, String trackUrl,
			String gpUrl, int priority, String adPositions, String channels,
			String countrys,String channelNames) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.trackUrl = trackUrl;
		this.gpUrl = gpUrl;
		this.priority = priority;
		this.adPositions = adPositions;
		this.channels = channels;
		this.countrys = countrys;
		this.channelNames = channelNames;
		this.updatedDate = new Date();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "name", length = 64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "packageName", length = 128)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Column(length = 512)
	public String getTrackUrl() {
		return trackUrl;
	}
	public void setTrackUrl(String trackUrl) {
		this.trackUrl = trackUrl;
	}
	@Column(length = 512)
	public String getGpUrl() {
		return gpUrl;
	}
	public void setGpUrl(String gpUrl) {
		this.gpUrl = gpUrl;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Lob
	public String getAdPositions() {
		return adPositions;
	}
	public void setAdPositions(String adPositions) {
		this.adPositions = adPositions;
	}
	@Lob
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	@Lob
	public String getCountrys() {
		return countrys;
	}
	public void setCountrys(String countrys) {
		this.countrys = countrys;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Lob
	public String getChannelNames() {
		return channelNames;
	}
	public void setChannelNames(String channelNames) {
		this.channelNames = channelNames;
	}

	
	
}
