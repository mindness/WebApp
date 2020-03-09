package com.mindness.webappback.controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import com.mindness.webappback.exception.ResourceNotFoundException;
import com.mindness.webappback.model.Film;
import com.mindness.webappback.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class FilmController {
    LocalDate ldt;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable(value = "id") Long filmId)
            throws ResourceNotFoundException {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + filmId));
        return ResponseEntity.ok().body(film);
    }

    @PostMapping("/films")
    public Film createFilm(@Valid @RequestBody Film film) {

        ldt = LocalDate.parse(film.getDateSortie().substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        film.setDateSortie(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE).format(ldt));

        return filmRepository.save(film);
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable(value = "id") Long filmId,
                                           @Valid @RequestBody Film filmDetails) throws ResourceNotFoundException, ParseException {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + filmId));

        ldt = LocalDate.parse(filmDetails.getDateSortie().substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1); //  add 1 jours pour compenser le fait que java en enleve 1
        film.setName(filmDetails.getName());
        film.setAffiche(filmDetails.getAffiche());
        film.setDateSortie(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE).format(ldt));
        film.setNote(filmDetails.getNote());
        film.setSynopsis(filmDetails.getSynopsis());
        final Film updatedFilm = filmRepository.save(film);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/films/{id}")
    public Map<String, Boolean> deleteFilm(@PathVariable(value = "id") Long filmId)
            throws ResourceNotFoundException {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + filmId));

        filmRepository.delete(film);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
