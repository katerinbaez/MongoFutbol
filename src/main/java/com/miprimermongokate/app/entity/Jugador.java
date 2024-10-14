package com.miprimermongokate.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jugadores")
public class Jugador {

    @Id
    private String id; // Cambiado a String para almacenar el ID como cadena
    private static long contador = 1; // Contador estático para generar IDs únicos

    private String nombre;
    private String apellido;
    private int numero;
    private String posicion;

    // Constructor sin parámetros
    public Jugador() {
        this.id = String.valueOf(contador++); // Generar ID como número y convertir a String
    }

    // Constructor con parámetros
    public Jugador(String nombre, String apellido, int numero, String posicion) {
        this.id = String.valueOf(contador++); // Generar ID como número y convertir a String
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.posicion = posicion;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    // Método estático para obtener el contador
    public static long getContador() {
        return contador;
    }
}
