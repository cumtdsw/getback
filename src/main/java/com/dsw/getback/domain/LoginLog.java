package com.dsw.getback.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户管理_托管应用(GFGX_Y_YHGL_TGYY)
 */
@Entity
@Table(name = "LoginLog")
public class LoginLog {

	@Id
	@Column(name = "loginLogId")
	private String id;
	private String userId;
	private String ip;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date loginDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

}
