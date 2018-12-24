package com.nishant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nishant.components.QuestionComp;
import com.nishant.database.QuestionSetRepository;
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
	@RequestMapping(path = "/addQuestion/", method = RequestMethod.POST)
	public String postQuestion(@RequestBody QuestionSet ques ) throws NumberFormatException, InterruptedException, JsonProcessingException {
		System.out.println("adding Question");
		
		questionSetRepository.save(ques);

		 return "";
	}
	
	@RequestMapping(path = "/submit/", method = RequestMethod.GET)
	public void submitAns(@RequestHeader(value="emailId") String emailId, @RequestHeader(value="time") String time,@RequestHeader(value="ans") String ans) {
		
	}

}
