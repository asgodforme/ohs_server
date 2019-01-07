package com.jiangjie.ohs.dto;

import java.util.List;

public class Menu {

	private String id;

	private String role;

	private String parentMenuName;

	private String parentMenuDesc;

	private List<SubMenu> subMenus;

	public String getParentMenuDesc() {
		return parentMenuDesc;
	}

	public void setParentMenuDesc(String parentMenuDesc) {
		this.parentMenuDesc = parentMenuDesc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public List<SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
