package com.miprimermongokate.app.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miprimermongokate.app.Repository.AsociacionRepository;
import com.miprimermongokate.app.entity.Asociacion;
import com.miprimermongokate.app.entity.Club;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/asociaciones")
public class AsociacionRestController {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping("/")
    public List<Asociacion> getAllAsociaciones() {
        return asociacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Asociacion getAsociacionById(@PathVariable String id) {
        return asociacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociación no encontrada"));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Asociacion saveAsociacion(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
        return asociacionRepository.save(asociacion);
    }

    @PutMapping("/{id}")
    public Asociacion updateAsociacion(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
        asociacion.setId(id);
        return asociacionRepository.save(asociacion);
    }

    @DeleteMapping("/{id}")
    public void deleteAsociacion(@PathVariable String id) {
        Asociacion asociacion = asociacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociacion no encontrada"));
        asociacionRepository.deleteById(id);
    }
}
