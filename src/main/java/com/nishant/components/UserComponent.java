package com.nishant.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nishant.database.UserRepository;
import com.nishant.model.User;

@Component
public class UserComponent {
	@Autowired
	UserRepository userRepository;
	
	private List<User> userList;
	
	public UserComponent() {
		System.out.println("here2");
		if(userList==null)
			userList = new ArrayList<>();
	}
	public void addUser(User user) {
		userList.add(user);
	}
	public List getUser() {
		return userList;
	}
}
