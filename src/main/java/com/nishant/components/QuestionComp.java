package com.nishant.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nishant.database.QuestionSetRepository;
import com.nishant.database.ResultSetRepo;
import com.nishant.model.CurrLeaderModel;
import com.nishant.model.QuestionSet;
import com.nishant.model.ResultSetModel;
import com.nishant.model.User;
import com.nishant.shared.CurrentLeaderBoard;

@Component
public class QuestionComp {

	long waitTime;
	long startTime;
	List<QuestionSet> quesLst; // get complete list from DB
	QuestionSet ques;
//	static QuestionSet;
	int quesCnt = 0;
	Date date;

	@Autowired
	QuestionSetRepository questionSetRepository;
	@Autowired
	ResultSetRepo resultSetRepo;

	@PostConstruct
	public void init() {
		if (quesLst == null) {
//			quesLst = new ArrayList<>();
//			quesLst.add(new QuestionSet("mult", "What is docker", "111", "Language", "ORM", "machine", "devOps", "devOps", "20", "10"));
//			quesLst.add(new QuestionSet("mult", "What is pocker", "222", "Language", "game", "machine", "devOps", "game", "20", "10"));
//			quesLst.add(new QuestionSet("mult", "What is mocker", "333", "invalid", "ORM", "machine", "devOps", "invalid", "20", "10"));
			quesLst = questionSetRepository.findAll();
			ques = quesLst.get(0);
			waitTime = Long.parseLong(ques.getqTimeLmt()) * 1000;
			startTime = (new Date()).getTime();
			CurrentLeaderBoard.currLeaderList = new ArrayList<>();
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
		if (currTime >= startTime + waitTime) {
			CurrentLeaderBoard.currLeaderList = new ArrayList<>();
			quesCnt++;
			ques = quesLst.get(quesCnt);
			waitTime = Long.parseLong(ques.getqTimeLmt()) * 1000;
			startTime = currTime;
		}
		return ques;
	}

	public String getResult(String email, String time, String selctOpt, String qId) {
		System.out.println("get result called....");
		long id = Long.parseLong(qId);
		String points = "0";
		if (id == ques.getqId()) {
			System.out.println("qid Matched, selctedOption= " + selctOpt.trim() + " Correct Ans=> " + ques.getAns());
			if (selctOpt.trim().equalsIgnoreCase(ques.getAns().trim())) {
				points = ques.getqPnts();
			}
		} else {
			Optional<QuestionSet> dbResp = questionSetRepository.findById(id);
			if (dbResp.isPresent()) {
				QuestionSet quesFrmDb = dbResp.get();
				if (selctOpt.trim().equalsIgnoreCase(quesFrmDb.getAns().trim())) {
					points = quesFrmDb.getqPnts();
				}
			}
		}

		CurrLeaderModel currLedrObj = new CurrLeaderModel(email, time, selctOpt, qId, points);
		CurrentLeaderBoard.currLeaderList.add(currLedrObj);
		System.out.println("result-> " + ques.getqId() + " " + Long.parseLong(qId));

		Optional<ResultSetModel> resultFrmDB = resultSetRepo.findById(email);
		if (resultFrmDB.isPresent()) {
			ResultSetModel resultSet = resultFrmDB.get();
			Map<String, CurrLeaderModel> currMap = resultSet.getCurrLeaderModel();
			currMap.put(qId, currLedrObj);
			resultSetRepo.save(new ResultSetModel(email, currMap));
			System.out.println("Save result in DB");
		} else {
			Map<String, CurrLeaderModel> map = new HashMap<>();
			map.put(qId, currLedrObj);
			resultSetRepo.save(new ResultSetModel(email, map));
			System.out.println("Save result in DB_1");
		}

		System.out.println("points " + points);
		return points;
	}
}
