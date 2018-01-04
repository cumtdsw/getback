package com.dsw.getback.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@Column(name = "userId")
	private String id;
	private String username;
	private String password;
	private String lastIp;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date lastVisit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	
	@Override
	public String toString() {
		return "[user.id:" + id + ", user.username" + username + ",user.password:" + password + ", lastip:" + lastIp + ", lastVist" + lastVisit + "]";
	}

}
