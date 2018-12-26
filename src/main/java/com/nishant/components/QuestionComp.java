package com.nishant.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nishant.database.QuestionSetRepository;
import com.nishant.model.QuestionSet;

@Component
public class QuestionComp {
	
	long waitTime;
	long startTime;
	List<QuestionSet> quesLst;   				//get complete list from DB
	QuestionSet ques;
//	static QuestionSet;
	int quesCnt=0;
	Date date;
	
	@Autowired
	QuestionSetRepository questionSetRepository;
	
	@PostConstruct
	public void init() {
		if(quesLst==null) {
//			quesLst = new ArrayList<>();
//			quesLst.add(new QuestionSet("mult", "What is docker", "111", "Language", "ORM", "machine", "devOps", "devOps", "20", "10"));
//			quesLst.add(new QuestionSet("mult", "What is pocker", "222", "Language", "game", "machine", "devOps", "game", "20", "10"));
//			quesLst.add(new QuestionSet("mult", "What is mocker", "333", "invalid", "ORM", "machine", "devOps", "invalid", "20", "10"));
			quesLst = questionSetRepository.findAll();
			ques = quesLst.get(0);
			waitTime = Long.parseLong(ques.getqTimeLmt())*1000;
			startTime = (new Date()).getTime();
		}
	}
//	public QuestionSet getQuestion() throws NumberFormatException, InterruptedException {
//		if(quesCnt==0) {
//			waitTime = (Long.parseLong(ques.getqTimeLmt()));
//			quesCnt++;
//			return ques;
//			}
//		Thread.sleep(1000*waitTime);
//		if(quesCnt<quesLst.size()) {
//			waitTime = (Long.parseLong(ques.getqTimeLmt()));
//			ques = quesLst.get(quesCnt);
//			quesCnt=quesCnt+1;
//			return ques;
//		}else
//		return null;
//	}
//	
	public QuestionSet getQuestion() throws NumberFormatException, InterruptedException {
		date = new Date();
		long currTime = date.getTime();
	    if(currTime>=startTime+waitTime) {
	    	quesCnt++;
	    	ques = quesLst.get(quesCnt);
	    	waitTime = Long.parseLong(ques.getqTimeLmt())*1000;
	    	startTime = currTime;
	    }
		return ques;
	}
	
	public int getResult(String email, String time,String ans) {
		if(ans.trim().equals(ques.getAns().trim())) {
			
		}
		return 0;
	}
}
