package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "area",
uniqueConstraints={@UniqueConstraint(columnNames = {"country","province","city"})})
//alter table area add index index_province_city(province,city)
//alter table area drop index province;
//alter table area add unique(country,province,city);
public class GArea {
	
	private Integer id;		
	private String country;//国家
	private String province;//省份
	private String city;//城市
	
	public GArea(){}
	public GArea(String country,String province, String city) {
		super();
		this.country = country;
		this.province = province;
		this.city = city;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "country",  length = 64) 
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "province",  length = 64) 
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name = "city",  length = 64) 
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
