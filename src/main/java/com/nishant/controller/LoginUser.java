package com.nishant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.model.User;

@RestController
public class LoginUser {

	@RequestMapping(path = "/login/", method = RequestMethod.POST)
	public void userLogin(@RequestBody User user) {
	}
}
