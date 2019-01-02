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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nishant.database.QuestionSetRepository;
import com.nishant.database.UserRepository;
import com.nishant.model.User;

@RestController
public class LoginUser {
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuestionSetRepository questionSetRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(path = "/login/", method = RequestMethod.POST)
	public Map userLogin(@RequestBody User userInpCred) {
		ModelAndView modelAndView = new ModelAndView();
		Map response = new HashMap<>();
		boolean isAdmin = false;
		boolean isValid = false;
		String userEmail = "invalid";
		String view = "error";
		String email = userInpCred.getEmail();
		String pwd = userInpCred.getPassword();
		Optional<User> result = userRepository.findById(email);
		if (result.isPresent()) {
			userEmail = email;
			view = "homepage";
			modelAndView.addObject("email", userInpCred.getEmail());
			User user = result.get();
			if (userInpCred.getPassword().equalsIgnoreCase(user.getPassword())) {
				isValid = true;
				if (user.getRole().equalsIgnoreCase("admin")) {
					isAdmin = true;
					view = "adminHomepage";
				}
			}
		}
		modelAndView.setViewName(view);
		response.put("isValid", isValid);
		response.put("isAdmin", isAdmin);
		response.put("email", userEmail);
		response.put("page", view);
		response.put("questions", questionSetRepository.count());
		return response;
	}
}
