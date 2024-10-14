package com.miprimermongokate.app.entity;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="competiciones")
public class Competicion {
    @Id
    private String id; // Cambiado a String como se requiere en el proyecto

    private String nombre; // Campo para el nombre de la competición
    private int montoPremio; // Campo para el monto del premio
    private LocalDate fechaInicio; // Campo para la fecha de inicio
    private LocalDate fechaFin; // Campo para la fecha de fin

    // Constructor
    public Competicion(String id, String nombre, int montoPremio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id; // Inicializa el ID
        this.nombre = nombre; // Inicializa el nombre
        this.montoPremio = montoPremio; // Inicializa el monto del premio
        this.fechaInicio = fechaInicio; // Inicializa la fecha de inicio
        this.fechaFin = fechaFin; // Inicializa la fecha de fin
    }

    // Getters y setters
    public String getId() {
        return id; // Getter para el ID
    }

    public void setId(String id) {
        this.id = id; // Setter para el ID
    }

    public String getNombre() {
        return nombre; // Getter para el nombre
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Setter para el nombre
    }

    public int getMontoPremio() {
        return montoPremio; // Getter para el monto del premio
    }

    public void setMontoPremio(int montoPremio) {
        this.montoPremio = montoPremio; // Setter para el monto del premio
    }

    public LocalDate getFechaInicio() {
        return fechaInicio; // Getter para la fecha de inicio
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio; // Setter para la fecha de inicio
    }

    public LocalDate getFechaFin() {
        return fechaFin; // Getter para la fecha de fin
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin; // Setter para la fecha de fin
    }
}
