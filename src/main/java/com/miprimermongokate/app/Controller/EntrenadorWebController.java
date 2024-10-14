package com.miprimermongokate.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.miprimermongokate.app.Repository.EntrenadorRepository;
import com.miprimermongokate.app.entity.Entrenador;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorWebController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    // Método para listar entrenadores
    @GetMapping("/")
    public String entrenadoresListTemplate(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenadores-list"; // Asegúrate que este es el nombre correcto de tu plantilla
    }

    // Método para mostrar el formulario de nuevo entrenador
    @GetMapping("/new")
    public String entrenadoresNewTemplate(Model model) {
        model.addAttribute("entrenador", new Entrenador("", "", 0, ""));
        return "entrenadores-form"; // Asegúrate que este es el nombre correcto de tu plantilla
    }

    // Método para editar un entrenador
    @GetMapping("/edit/{id}")
    public String entrenadoresEditTemplate(@PathVariable("id") String id, Model model) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado"));
        model.addAttribute("entrenador", entrenador);
        return "entrenadores-form"; // Asegúrate que este es el nombre correcto de tu plantilla
    }

    // Método para guardar o actualizar un entrenador
    @PostMapping("/save")
    public String entrenadoresSaveProcess(@ModelAttribute("entrenador") Entrenador entrenador) {
        // Si el ID es nulo o vacío, MongoDB lo generará automáticamente
        if (entrenador.getId() == null || entrenador.getId().isEmpty()) {
            entrenador.setId(null); 
        }
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenadores/";
    }

    // Método para eliminar un entrenador
    @GetMapping("/delete/{id}")
    public String entrenadoresDeleteProcess(@PathVariable("id") String id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado"));
        
        entrenadorRepository.delete(entrenador);
        return "redirect:/entrenadores/";
    }
}
