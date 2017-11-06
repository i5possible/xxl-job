package com.xxl.job.core.biz.model;

import java.io.Serializable;

public class ExecuteResult implements Serializable {
	
	private static final long serialVersionUID = 42L;
	
	private String state;
	private String startTime;
	private String endTime;
	private String msg;
	private String detailInfo;

	public ExecuteResult(){}
	
	public ExecuteResult(String state, String startTime, String endTime, String msg, String detailInfo) {
		this.state = state;
		this.startTime = startTime;
		this.endTime = endTime;
		this.msg = msg;
		this.detailInfo = detailInfo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}
	
}
