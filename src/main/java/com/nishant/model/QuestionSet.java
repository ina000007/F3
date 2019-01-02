package com.nishant.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import com.fasterxml.jackson.annotation.JsonView;
import com.mongodb.Mongo;
import com.nishant.database.DatabaseSequence;
import com.nishant.view.Views;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Document
public class QuestionSet {
	
	@Transient
    public static final String SEQUENCE_NAME = "ques_sequence";
    
	@JsonView(Views.Public.class)
	private String qTyp;
	@JsonView(Views.Public.class)
	private String qDesc;
	@JsonView(Views.Public.class)
	private String qNum;
	@JsonView(Views.Public.class)
	private String qOpt1;
	@JsonView(Views.Public.class)
	private String qOpt2;
	@JsonView(Views.Public.class)
	private String qOpt3;
	@JsonView(Views.Public.class)
	private String qOpt4;
	@JsonView(Views.Public.class)
	private String qTimeLmt;
	@JsonView(Views.Public.class)
	private String qPnts;
	
	@JsonView(Views.Internal.class)
	private String ans;
	
	@Id
	@JsonView(Views.Public.class)
	long qId;
	
	
	
	public QuestionSet(String qTyp, String qDesc, String qNum, String qOpt1, String qOpt2, String qOpt3, String qOpt4,
			String ans, String qTimeLmt, String qPnts) {
		super();
		this.qTyp = qTyp;
		this.qDesc = qDesc;
		this.qNum = qNum;
		this.qOpt1 = qOpt1;
		this.qOpt2 = qOpt2;
		this.qOpt3 = qOpt3;
		this.qOpt4 = qOpt4;
		this.ans = ans;
		this.qTimeLmt = qTimeLmt;
		this.qPnts = qPnts;
//		this.qId = databaseSequence.getNextSequence(SEQUENCE_NAME);
		this.qId = 12;
	}
	
	public long getqId() {
		return qId;
	}

	public void setqId(long qId) {
		this.qId = qId;
	}

	public String getqTyp() {
		return qTyp;
	}
	public void setqTyp(String qTyp) {
		this.qTyp = qTyp;
	}
	public String getqDesc() {
		return qDesc;
	}
	public void setqDesc(String qDesc) {
		this.qDesc = qDesc;
	}
	public String getqNum() {
		return qNum;
	}
	public void setqNum(String qNum) {
		this.qNum = qNum;
	}
	public String getqOpt1() {
		return qOpt1;
	}
	public void setqOpt1(String qOpt1) {
		this.qOpt1 = qOpt1;
	}
	public String getqOpt2() {
		return qOpt2;
	}
	public void setqOpt2(String qOpt2) {
		this.qOpt2 = qOpt2;
	}
	public String getqOpt3() {
		return qOpt3;
	}
	public void setqOpt3(String qOpt3) {
		this.qOpt3 = qOpt3;
	}
	public String getqOpt4() {
		return qOpt4;
	}
	public void setqOpt4(String qOpt4) {
		this.qOpt4 = qOpt4;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getqTimeLmt() {
		return qTimeLmt;
	}
	public void setqTimeLmt(String qTimeLmt) {
		this.qTimeLmt = qTimeLmt;
	}
	public String getqPnts() {
		return qPnts;
	}
	public void setqPnts(String qPnts) {
		this.qPnts = qPnts;
	}
	
    @Override
	public String toString() {
		return "QuestionSet [qTyp=" + qTyp + ", qDesc=" + qDesc + ", qNum=" + qNum + ", qOpt1=" + qOpt1 + ", qOpt2="
				+ qOpt2 + ", qOpt3=" + qOpt3 + ", qOpt4=" + qOpt4 + ", qTimeLmt=" + qTimeLmt + ", qPnts=" + qPnts
				+ ", ans=" + ans + ", qId=" + qId + "]";
	}

}
