package com.nishant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CurrLeaderModel {
	public CurrLeaderModel(String email, String time, String selctOpt, String qId, String points) {
		super();
		this.email = email;
		this.time = time;
		this.selctOpt = selctOpt;
		this.qId = qId;
		this.points = points;
	}
	
	String email; 
	String time; 
	String selctOpt;
	@Id
	String qId;
	String points;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSelctOpt() {
		return selctOpt;
	}
	public void setSelctOpt(String selctOpt) {
		this.selctOpt = selctOpt;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
}
