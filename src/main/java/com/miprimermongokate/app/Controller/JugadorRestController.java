package com.miprimermongokate.app.Controller;

import com.miprimermongokate.app.entity.Jugador;
import com.miprimermongokate.app.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores/")
public class JugadorRestController {

    @Autowired
    private JugadorRepository jugadorRepository;

    // Obtener todos los jugadores
    @GetMapping
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorRepository.findAll();
    }

    // Obtener un jugador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable String id) {
        Optional<Jugador> jugador = jugadorRepository.findById(id);
        return jugador.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo jugador
    @PostMapping
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
        Jugador nuevoJugador = jugadorRepository.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoJugador);
    }

    // Actualizar un jugador existente
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable String id, @RequestBody Jugador jugadorActualizado) {
        if (!jugadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jugadorActualizado.setId(id);
        Jugador jugadorGuardado = jugadorRepository.save(jugadorActualizado);
        return ResponseEntity.ok(jugadorGuardado);
    }

    // Eliminar un jugador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable String id) {
        if (!jugadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jugadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
