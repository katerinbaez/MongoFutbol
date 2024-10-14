package com.miprimermongokate.app.Controller;

import com.miprimermongokate.app.entity.Asociacion;
import com.miprimermongokate.app.entity.Club;
import com.miprimermongokate.app.entity.Competicion;
import com.miprimermongokate.app.entity.Entrenador;
import com.miprimermongokate.app.entity.Jugador;
import com.miprimermongokate.app.Repository.AsociacionRepository;
import com.miprimermongokate.app.Repository.ClubRepository;
import com.miprimermongokate.app.Repository.CompeticionRepository;
import com.miprimermongokate.app.Repository.EntrenadorRepository;
import com.miprimermongokate.app.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clubes")
public class ClubWebController {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Autowired
    private CompeticionRepository competicionRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/")
    public String clubesListTemplate(Model model) {
        model.addAttribute("clubes", clubRepository.findAll());
        return "clubes-list"; // Asegúrate que este es el nombre correcto de tu plantilla
    }

    @GetMapping("/new")
    public String createClubForm(Model model) {
        model.addAttribute("club", new Club());
        populateModelWithEntities(model);
        return "clubes-form";  // Nombre de la vista HTML para crear un nuevo club
    }

    @PostMapping("/save")
    public String clubesSaveProcess(@ModelAttribute("club") Club club) {
        populateEntities(club);
        clubRepository.save(club);
        return "redirect:/clubes/"; // Redirige a la lista de clubes
    }

    @GetMapping("/edit/{id}")
    public String editClubForm(@PathVariable String id, Model model) {
        Club club = clubRepository.findById(id).orElse(null);
        if (club == null) {
            return "redirect:/clubes/"; // Redirigir si el club no existe
        }
        model.addAttribute("club", club);
        populateModelWithEntities(model);
        return "clubes-form";  // Nombre de la vista HTML para editar un club
    }

    @GetMapping("/delete/{id}")
    public String deleteClub(@PathVariable String id) {
        clubRepository.deleteById(id);
        return "redirect:/clubes/";  // Redirige a la lista de clubes
    }

    private void populateModelWithEntities(Model model) {
        List<Asociacion> asociaciones = asociacionRepository.findAll();
        List<Competicion> competiciones = competicionRepository.findAll();
        List<Entrenador> entrenadores = entrenadorRepository.findAll();
        List<Jugador> jugadores = jugadorRepository.findAll();

        model.addAttribute("asociaciones", asociaciones);
        model.addAttribute("competiciones", competiciones);
        model.addAttribute("entrenadores", entrenadores);
        model.addAttribute("jugadores", jugadores);
    }

    private void populateEntities(Club club) {
        // Asignar entrenadores a un club
        if (club.getEntrenadores() != null) {
            List<Entrenador> entrenadores = new ArrayList<>();
            for (Entrenador entrenador : club.getEntrenadores()) {
                entrenadorRepository.findById(entrenador.getId()).ifPresent(entrenadores::add);
            }
            club.setEntrenadores(entrenadores);
        }

        // Asignar asociaciones a un club
        if (club.getAsociaciones() != null) {
            List<Asociacion> asociaciones = new ArrayList<>();
            for (Asociacion asociacion : club.getAsociaciones()) {
                asociacionRepository.findById(asociacion.getId()).ifPresent(asociaciones::add);
            }
            club.setAsociaciones(asociaciones);
        }

        // Asignar competiciones a un club
        if (club.getCompeticiones() != null) {
            List<Competicion> competiciones = new ArrayList<>();
            for (Competicion competicion : club.getCompeticiones()) {
                competicionRepository.findById(competicion.getId()).ifPresent(competiciones::add);
            }
            club.setCompeticiones(competiciones);
        }

        // Asignar jugadores a un club
        if (club.getJugadores() != null) {
            List<Jugador> jugadores = new ArrayList<>();
            for (Jugador jugador : club.getJugadores()) {
                jugadorRepository.findById(jugador.getId()).ifPresent(jugadores::add);
            }
            club.setJugadores(jugadores);
        }
    }
}
