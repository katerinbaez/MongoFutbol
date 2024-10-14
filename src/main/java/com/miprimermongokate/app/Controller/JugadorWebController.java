package com.miprimermongokate.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.miprimermongokate.app.Exception.NotFoundException;
import com.miprimermongokate.app.Repository.JugadorRepository;
import com.miprimermongokate.app.entity.Jugador;

@Controller
@RequestMapping("/jugadores")
public class JugadorWebController {

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/")
    public String jugadoresListTemplate(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        return "jugadores-list"; // Nombre de la plantilla Thymeleaf
    }

    @GetMapping("/new")
    public String jugadorNewTemplate(Model model) {
        model.addAttribute("jugador", new Jugador()); // Inicializa un nuevo jugador
        return "jugadores-form"; // Nombre de la plantilla Thymeleaf
    }

    @GetMapping("/edit/{id}")
    public String jugadorEditTemplate(@PathVariable("id") String id, Model model) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
        model.addAttribute("jugador", jugador);
        return "jugadores-form"; // Nombre de la plantilla Thymeleaf
    }

    @PostMapping("/save")
    public String jugadoresSaveProcess(@ModelAttribute("jugador") Jugador jugador) {
        // MongoDB gestionará el ID automáticamente si se genera en la entidad
        if (jugador.getId() == null || jugador.getId().isEmpty()) {
            // Generar un nuevo ID basado en el contador en la clase Jugador
            jugador.setId(null); // El ID será generado en el constructor de la clase Jugador
        }
        jugadorRepository.save(jugador);
        return "redirect:/jugadores/";
    }

    @GetMapping("/delete/{id}")
    public String jugadorDeleteProcess(@PathVariable("id") String id) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
        jugadorRepository.deleteById(id);
        return "redirect:/jugadores/"; // Redirige a la lista de jugadores
    }
}
