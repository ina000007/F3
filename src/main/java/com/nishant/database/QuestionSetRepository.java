package com.nishant.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nishant.model.QuestionSet;

@Repository
public interface QuestionSetRepository extends MongoRepository<QuestionSet, Long>{

}
