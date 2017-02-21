package com.guang.web.mode;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_media")
public class GMedia {
	private Long id;
	private String name;
	private String packageName;// 包名
	private Boolean open;//是否开启
	private String adPosition;
	private Float loopTime;
	private Boolean uploadPackage;//是否上传包名
	
	
	private String adPositionName;
	private List<GAdPositionConfig> configs;
	public GMedia(){}
	
	public GMedia(String name, String packageName, Boolean open,
			String adPosition) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.open = open;
		this.adPosition = adPosition;
		this.loopTime = 24.f;
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
	@Column(length = 64)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	@Column(length = 128)
	public String getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
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
	
	
	
}
