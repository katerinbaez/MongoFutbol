package com.miprimermongokate.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miprimermongokate.app.Exception.NotFoundException;
import com.miprimermongokate.app.Repository.EntrenadorRepository;
import com.miprimermongokate.app.entity.Competicion;
import com.miprimermongokate.app.entity.Entrenador;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorRestController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping("/")
    public List<Entrenador> getAllEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getEntrenadorById(@PathVariable String id) {
        Optional<Entrenador> entrenador = entrenadorRepository.findById(id);
        return entrenador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Entrenador createEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> updateEntrenador(@PathVariable String id, @RequestBody Entrenador entrenadorDetails) {
        return entrenadorRepository.findById(id)
                .map(entrenador -> {
                    entrenador.setNombre(entrenadorDetails.getNombre());
                    entrenador.setApellido(entrenadorDetails.getApellido());
                    entrenador.setEdad(entrenadorDetails.getEdad());
                    entrenador.setNacionalidad(entrenadorDetails.getNacionalidad());
                    Entrenador updatedEntrenador = entrenadorRepository.save(entrenador);
                    return ResponseEntity.ok(updatedEntrenador);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteEntrenador(@PathVariable String id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrenador no encontrado"));
        entrenadorRepository.deleteById(id);
    }}
