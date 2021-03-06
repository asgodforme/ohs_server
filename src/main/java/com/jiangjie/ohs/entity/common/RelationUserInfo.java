package com.jiangjie.ohs.entity.common;

import java.sql.Timestamp;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公共的创建人，创建时间，修改人，修改时间
 * 
 * @author Administrator
 *
 */
@Embeddable
public class RelationUserInfo {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp createDate;

	private String createUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp updateDate;

	private String updateUser;

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "RelationUserInfo [createDate=" + createDate + ", createUser=" + createUser + ", updateDate="
				+ updateDate + ", updateUser=" + updateUser + "]";
	}

}
