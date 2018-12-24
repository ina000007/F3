package com.nishant.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nishant.model.User;

@Repository
public interface UserRepository extends  MongoRepository<User, String> {

}
