package com.miprimermongokate.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miprimermongokate.app.entity.Jugador;



public interface JugadorRepository extends MongoRepository<Jugador, String >  {

}
