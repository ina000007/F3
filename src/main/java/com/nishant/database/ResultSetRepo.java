package com.nishant.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nishant.model.QuestionSet;
import com.nishant.model.ResultSetModel;

@Repository
public interface ResultSetRepo extends MongoRepository<ResultSetModel, String>{

}
