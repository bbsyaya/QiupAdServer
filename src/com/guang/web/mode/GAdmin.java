package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_admin")
public class GAdmin {
	private long id;
	private long permissionsId;
	private String name;
	private String password;
	
	private GPermission permission;

	public GAdmin(){}
	public GAdmin(long permissionsId, String name, String password) {
		super();
		this.permissionsId = permissionsId;
		this.name = name;
		this.password = password;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getPermissionsId() {
		return permissionsId;
	}
	public void setPermissionsId(long permissionsId) {
		this.permissionsId = permissionsId;
	}
	@Column(nullable = false, length = 64, unique = true)
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
	@Transient
	public GPermission getPermission() {
		return permission;
	}
	public void setPermission(GPermission permission) {
		this.permission = permission;
	}
	
	
}
