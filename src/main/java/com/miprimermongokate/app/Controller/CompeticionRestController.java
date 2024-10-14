package com.miprimermongokate.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miprimermongokate.app.Exception.NotFoundException;
import com.miprimermongokate.app.Repository.CompeticionRepository;
import com.miprimermongokate.app.entity.Competicion;

import java.util.List;

@RestController
@RequestMapping("/api/competiciones")
public class CompeticionRestController {

    @Autowired
    private CompeticionRepository competicionRepository;

    @GetMapping
    public List<Competicion> getAllCompeticiones() {
        return competicionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Competicion getCompeticionById(@PathVariable String id) {
        return competicionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Competición no encontrada"));
    }

    @PostMapping
    public Competicion saveCompeticion(@RequestBody Competicion competicion) {
        // Se puede considerar la validación del objeto competicion aquí
        return competicionRepository.save(competicion);
    }

    @PutMapping("/{id}")
    public Competicion updateCompeticion(@PathVariable String id, @RequestBody Competicion competicion) {
        Competicion existingCompeticion = competicionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Competición no encontrada"));
        
        // Actualizar solo los campos necesarios para evitar la sobrescritura accidental
        existingCompeticion.setNombre(competicion.getNombre());
        existingCompeticion.setMontoPremio(competicion.getMontoPremio());
        existingCompeticion.setFechaInicio(competicion.getFechaInicio());
        existingCompeticion.setFechaFin(competicion.getFechaFin());
        
        return competicionRepository.save(existingCompeticion);
    }

    @DeleteMapping("/{id}")
    public void deleteCompeticion(@PathVariable String id) {
        Competicion competicion = competicionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Competición no encontrada"));
        
        competicionRepository.delete(competicion); // Cambiado a delete(competicion) para mayor claridad
    }
}
