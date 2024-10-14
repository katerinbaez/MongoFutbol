package com.miprimermongokate.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miprimermongokate.app.entity.Competicion;


public interface CompeticionRepository extends MongoRepository<Competicion, String>  {

}
