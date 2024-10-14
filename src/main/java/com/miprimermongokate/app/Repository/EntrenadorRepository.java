package com.miprimermongokate.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miprimermongokate.app.entity.Entrenador;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String>  {

}
