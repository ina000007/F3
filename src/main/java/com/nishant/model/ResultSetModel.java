package com.nishant.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResultSetModel {
	@Id
	String emailId;
	Map<String,CurrLeaderModel> currLeaderModel;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Map<String, CurrLeaderModel> getCurrLeaderModel() {
		return currLeaderModel;
	}
	public void setCurrLeaderModel(Map<String, CurrLeaderModel> currLeaderModel) {
		this.currLeaderModel = currLeaderModel;
	}
	public ResultSetModel(String emailId, Map<String, CurrLeaderModel> currLeaderModel) {
		super();
		this.emailId = emailId;
		this.currLeaderModel = currLeaderModel;
	}
	
	

	
}
