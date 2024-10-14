package com.miprimermongokate.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "clubes")
public class Club {
    @Id
    private String id;
    private String nombre;

    @DBRef
    private List<Asociacion> asociaciones;

    @DBRef
    private List<Entrenador> entrenadores;

    @DBRef
    private List<Competicion> competiciones;

    @DBRef
    private List<Jugador> jugadores;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Asociacion> getAsociaciones() {
        return asociaciones;
    }

    public void setAsociaciones(List<Asociacion> asociaciones) {
        this.asociaciones = asociaciones;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public List<Competicion> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Competicion> competiciones) {
        this.competiciones = competiciones;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
