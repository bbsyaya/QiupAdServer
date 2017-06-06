package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "phone_model",uniqueConstraints={@UniqueConstraint(columnNames = {"model","channel"})})
//alter table phone_model add index index_model(model)
//alter table phone_model add index index_channel(channel)
public class GPhoneModel {	
	private Integer id;	
	private String model;
	private String channel;//渠道

	public GPhoneModel() {
	}

	public GPhoneModel(String model,String channel) {
		super();
		this.model = model;
		this.channel = channel;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "model", length = 64)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	@Column(name = "channel", length = 64)
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
