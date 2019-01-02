package com.nishant.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResultSetModel {
	@Id
	String emailId;
	List<CurrLeaderModel> currLeaderModel;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<CurrLeaderModel> getCurrLeaderModel() {
		return currLeaderModel;
	}
	public void setCurrLeaderModel(List<CurrLeaderModel> currLeaderModel) {
		this.currLeaderModel = currLeaderModel;
	}
	public ResultSetModel(String emailId, List<CurrLeaderModel> currLeaderModel) {
		super();
		this.emailId = emailId;
		this.currLeaderModel = currLeaderModel;
	}
	
	
}
