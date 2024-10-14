package com.miprimermongokate.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miprimermongokate.app.entity.Club;

public interface ClubRepository extends MongoRepository<Club, String> {

}


