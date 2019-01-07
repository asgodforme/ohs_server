package com.jiangjie.ohs.security.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OhsSysRoleUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int sysUserId;

	private int sysRoleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	public int getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(int sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

}
