package com.dsw.getback.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LostArticlePubInfo")
public class LostArticlePubInfo {
	@Id
	private String id;
	private String laname;
	private String laescribe;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date lapubTime;
	private byte[] lapics;
	private String lauseridPub;
	private String lauserNamePub;
	private String lauserPhonePub;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLaname() {
		return laname;
	}


	public void setLaname(String laname) {
		this.laname = laname;
	}


	public String getLaescribe() {
		return laescribe;
	}


	public void setLaescribe(String laescribe) {
		this.laescribe = laescribe;
	}


	public Date getLapubTime() {
		return lapubTime;
	}
	
	public void setLapubTime(Date lapubTime) {
		this.lapubTime = lapubTime;
	}

	public byte[] getLapics() {
		return lapics;
	}


	public void setLapics(byte[] lapics) {
		this.lapics = lapics;
	}


	public String getLauseridPub() {
		return lauseridPub;
	}


	public void setLauseridPub(String lauseridPub) {
		this.lauseridPub = lauseridPub;
	}


	public String getLauserNamePub() {
		return lauserNamePub;
	}


	public void setLauserNamePub(String lauserNamePub) {
		this.lauserNamePub = lauserNamePub;
	}


	public String getLauserPhonePub() {
		return lauserPhonePub;
	}


	public void setLauserPhonePub(String lauserPhonePub) {
		this.lauserPhonePub = lauserPhonePub;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:" + id + ", name:" + laname;
	}

}
