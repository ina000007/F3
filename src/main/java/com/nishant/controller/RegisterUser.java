package com.nishant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.components.UserComponent;
import com.nishant.database.UserRepository;
import com.nishant.model.User;

@RestController
public class RegisterUser {
	@Autowired
	UserComponent userComponent;
	@Autowired
	UserRepository usersRepository;

//	public RegisterUser(UsersRepository userRepository) {
//		this.userRepository = userRepository;
//	}
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(path = "/register/", method = RequestMethod.POST)
	public Map serRegister(@RequestBody User user) {
		Map result=new HashMap();
		userComponent.addUser(user);
		if(!usersRepository.existsById(user.getEmail())) {
			System.out.println("new entry");
		result.put("status", "success");
		result.put("email", user.getEmail());
		usersRepository.save(user);
		}
		else
			result.put("status", "fail");
		return result;
	}

	@RequestMapping(path = "/getUser/", method = RequestMethod.GET)
	public ResponseEntity getRegstrdUser() {
		System.out.println("here3");
		List<User> usls = usersRepository.findAll();
		System.out.println("here\n:"+usls.toString());
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(usls, HttpStatus.OK);
		return responseEntity;
	}
    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

}
