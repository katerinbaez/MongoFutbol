package com.miprimermongokate.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.miprimermongokate.app.Repository.AsociacionRepository;
import com.miprimermongokate.app.entity.Asociacion;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping(value = "asociaciones")
public class AsociacionWebController {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping("/")
    public String asociacionesListTemplate(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "asociaciones-list";
    }

    @GetMapping("/new")
    public String asociacionesNewTemplate(Model model) {
        model.addAttribute("asociacion", new Asociacion("", "", "")); // Crear una nueva Asociación sin ID
        return "asociaciones-form";
    }

    @GetMapping("/edit/{id}")
    public String asociacionesEditTemplate(@PathVariable("id") String id, Model model) {
        Asociacion asociacion = asociacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociación no encontrada"));
        model.addAttribute("asociacion", asociacion);
        return "asociaciones-form";
    }

    @PostMapping("/save")
    public String asociacionesSaveProcess(@ModelAttribute("asociacion") Asociacion asociacion) {
        // Si el ID es nulo, se considera que es una nueva asociación
        if (asociacion.getId() == null || asociacion.getId().isEmpty()) {
            // Generar un nuevo ID basado en el contador en la clase Asociacion
            asociacion.setId(null); // Asegúrate de que el método en la entidad genere un nuevo ID
        }
        asociacionRepository.save(asociacion);
        return "redirect:/asociaciones/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAsociacion(@PathVariable String id) {
        asociacionRepository.deleteById(id);
        return "redirect:/asociaciones/";  
    }
}
