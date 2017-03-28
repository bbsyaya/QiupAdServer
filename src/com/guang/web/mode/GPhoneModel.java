package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_model")
//alter table phone_model add index index_model(model)
public class GPhoneModel {	
	private Integer id;	
	private String model;

	public GPhoneModel() {
	}

	public GPhoneModel(String model) {
		super();
		this.model = model;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "model", length = 64, unique = true)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
