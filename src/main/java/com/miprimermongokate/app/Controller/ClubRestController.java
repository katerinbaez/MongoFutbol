package com.miprimermongokate.app.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miprimermongokate.app.Repository.ClubRepository;
import com.miprimermongokate.app.entity.Asociacion;
import com.miprimermongokate.app.entity.Club;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/clubes")
public class ClubRestController {

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping("/")
    public List<Club> getAllClubes() {
        return clubRepository.findAll();
    }

    @GetMapping("/{id}")
    public Club getClubById(@PathVariable String id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Club no encontrado"));
    }

    @PostMapping("/")
    public Club createClub(@RequestBody Club club) {
        // Al crear un nuevo club, el ID se generará automáticamente por MongoDB
        return clubRepository.save(club);
    }
    @PutMapping("/{id}")
    public Club updateClub(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Club club = mapper.convertValue(body, Club.class);
        club.setId(id);
        return clubRepository.save(club);
    }
   

    @DeleteMapping("/{id}")
    public void deleteClub(@PathVariable String id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Club no encontrado"));
        clubRepository.deleteById(id);
    }
}
