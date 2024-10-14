package com.miprimermongokate.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.miprimermongokate.app.Repository.CompeticionRepository;
import com.miprimermongokate.app.entity.Competicion;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "/competiciones")
public class CompeticionWebController {

    @Autowired
    private CompeticionRepository competicionRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Listar competiciones
    @GetMapping("/")
    public String competicionesListTemplate(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competiciones-list"; // Asegúrate de que la plantilla esté en la ruta correcta
    }

    // Formulario para agregar nueva competición
    @GetMapping("/new")
    public String competicionesNewTemplate(Model model) {
        Competicion competicion = new Competicion(null, null, 0, null, null);
        model.addAttribute("competicion", competicion);
        model.addAttribute("formattedFechaInicio", "");
        model.addAttribute("formattedFechaFin", "");
        return "competiciones-form"; // Asegúrate de que la plantilla esté en la ruta correcta
    }

    // Formulario para editar una competición existente
    @GetMapping("/edit/{id}")
    public String competicionesEditTemplate(@PathVariable("id") String id, Model model) {
        Competicion competicion = competicionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Competicion no encontrada"));

        // Formatear las fechas para pasarlas a la vista
        String formattedFechaInicio = competicion.getFechaInicio() != null
                ? competicion.getFechaInicio().format(formatter)
                : "";
        String formattedFechaFin = competicion.getFechaFin() != null
                ? competicion.getFechaFin().format(formatter)
                : "";

        model.addAttribute("competicion", competicion);
        model.addAttribute("formattedFechaInicio", formattedFechaInicio);
        model.addAttribute("formattedFechaFin", formattedFechaFin);
        return "competiciones-form";
    }

    // Guardar la competición (nueva o actualizada)
    @PostMapping("/save")
    public String competicionesSaveProcess(@ModelAttribute("competicion") Competicion competicion) {
        competicionRepository.save(competicion); // Guarda la entidad, ya sea nueva o existente
        return "redirect:/competiciones/"; // Redirige a la lista después de guardar
    }

    // Eliminar una competición
    @GetMapping("/delete/{id}")
    public String competicionesDeleteProcess(@PathVariable("id") String id) {
        Competicion competicion = competicionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Competición no encontrada"));
        competicionRepository.deleteById(id); // Elimina la competición por ID
        return "redirect:/competiciones/"; // Redirige a la lista después de eliminar
    }
}
