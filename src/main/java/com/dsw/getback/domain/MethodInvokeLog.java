package com.dsw.getback.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MethodInvokeLog")
public class MethodInvokeLog {
	@Id
	private String id;
	
	/**执行方法名称*/
	private String methodInvokeName;
	
	/**方法执行时间*/
	private Date methodInvokeTime;
	
	/**方法执行效率*/
	private long methodInvokeEfficiency;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMethodInvokeName() {
		return methodInvokeName;
	}
	public void setMethodInvokeName(String methodInvokeName) {
		this.methodInvokeName = methodInvokeName;
	}
	public Date getMethodInvokeTime() {
		return methodInvokeTime;
	}
	public void setMethodInvokeTime(Date methodInvokeTime) {
		this.methodInvokeTime = methodInvokeTime;
	}
	public long getMethodInvokeEfficiency() {
		return methodInvokeEfficiency;
	}
	public void setMethodInvokeEfficiency(long methodInvokeEfficiency) {
		this.methodInvokeEfficiency = methodInvokeEfficiency;
	}
}
