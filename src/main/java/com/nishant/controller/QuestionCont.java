package com.nishant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nishant.components.QuestionComp;
import com.nishant.database.DatabaseSequence;
import com.nishant.database.QuestionSetRepository;
import com.nishant.database.UserRepository;
import com.nishant.model.QuestionSet;
import com.nishant.model.User;
import com.nishant.view.Views;

@RestController
public class QuestionCont {

	@Autowired
	QuestionComp quesComp;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	QuestionSetRepository questionSetRepository;
	@Autowired
	UserRepository userRepository;

	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(path = "/question/", method = RequestMethod.GET)
	public String getQuestion() throws NumberFormatException, InterruptedException, JsonProcessingException {
		System.out.println("here4");
		boolean forInternal = false;
		ObjectWriter viewWriter;
		if (forInternal) {
			viewWriter = mapper.writerWithView(Views.Internal.class);
		} else {
			viewWriter = mapper.writerWithView(Views.Public.class);
		}
		return viewWriter.writeValueAsString(quesComp.getQuestion());
	}
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(path = "/submit/", method = RequestMethod.POST)
	public Map submitAns(@RequestHeader(value = "emailId") String emailId, @RequestHeader(value = "time") String time,
			@RequestHeader(value = "selctOptn") String selctOptn, @RequestHeader(value = "quesId") String quesId) {
		System.out.println("submit is callled "+emailId+" "+time+" "+selctOptn+" "+quesId);
		String points = quesComp.getResult(emailId, time, selctOptn, quesId);
		Map response = new HashMap<>();
		response.put("points", points);
		return response;
	}

//==============================FOR ADMIN=============================
//	for admin
	@Autowired
	DatabaseSequence databaseSequence;

	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(path = "/addQuestion/", method = RequestMethod.POST)
	public Map postQuestion(@RequestBody QuestionSet ques, @RequestHeader(value = "emailid") String emailid)
			throws NumberFormatException, InterruptedException, JsonProcessingException {
		Map response = new HashMap<>();
		System.out.println("adding Question");
		Optional<User> result = userRepository.findById(emailid);
		if (result.isPresent()) {
			User user = result.get();

			if (user.getRole().equalsIgnoreCase("admin")) {
				ques.setqId(databaseSequence.getNextSequence("mymy_"));
				questionSetRepository.save(ques);
				response.put("status", "success");
				return response;
			}
		}
		response.put("status", "fail");
		return response;
	}

	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(path = "/getAllQuestion/", method = RequestMethod.GET)
	public List getAllQuestion(@RequestHeader(value = "emailid") String emailid)
			throws NumberFormatException, InterruptedException, JsonProcessingException {
		Optional<User> result = userRepository.findById(emailid);
		if (result.isPresent()) {
			User user = result.get();
			if (user.getRole().equalsIgnoreCase("admin"))
				return questionSetRepository.findAll();
		}
		return null;
	}

}
