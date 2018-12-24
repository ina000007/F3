package com.nishant.database;

import java.util.List;

import com.nishant.model.User;

public interface UserDAL {
	List<User> getAllUsers();

	User getUserById(String userId);

	User addNewUser(User user);

}
