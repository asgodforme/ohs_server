package com.jiangjie.ohs.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jiangjie.ohs.entity.common.RelationUserInfo;

/**
 * 菜单
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsMenu {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 角色 0-配置者， 1-使用者
	 */
	private String role;

	/**
	 * 父菜单id
	 */
	private String parentMenuId;

	/**
	 * 父菜单名
	 */
	private String parentMenuName;

	/**
	 * 父菜单路径
	 */
	private String parentUrl;

	/**
	 * 父菜单描述
	 */
	private String parentMenuDesc;

	/**
	 * 子菜单id
	 */
	private String subMenuId;

	/**
	 * 子菜单名
	 */
	private String subMenuName;

	/**
	 * 子菜单描述
	 */
	private String subMenuDesc;

	/**
	 * 子菜单路径
	 */
	private String subMenuUrl;

	/**
	 * 公共信息
	 */
	@Embedded
	private RelationUserInfo relationUserInfo;

	public String getParentMenuDesc() {
		return parentMenuDesc;
	}

	public void setParentMenuDesc(String parentMenuDesc) {
		this.parentMenuDesc = parentMenuDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public String getSubMenuDesc() {
		return subMenuDesc;
	}

	public void setSubMenuDesc(String subMenuDesc) {
		this.subMenuDesc = subMenuDesc;
	}

	public String getSubMenuUrl() {
		return subMenuUrl;
	}

	public void setSubMenuUrl(String subMenuUrl) {
		this.subMenuUrl = subMenuUrl;
	}

	public RelationUserInfo getRelationUserInfo() {
		return relationUserInfo;
	}

	public void setRelationUserInfo(RelationUserInfo relationUserInfo) {
		this.relationUserInfo = relationUserInfo;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}

}
