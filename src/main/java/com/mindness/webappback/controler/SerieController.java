package com.mindness.webappback.controler;

import com.mindness.webappback.exception.ResourceNotFoundException;
import com.mindness.webappback.model.Serie;
import com.mindness.webappback.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class SerieController {
    LocalDate ldt;

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/series")
    public List<Serie> getAllSerie() {
        return serieRepository.findAll();
    }

    @GetMapping("/series/{id}")
    public ResponseEntity<Serie> getSerieById(@PathVariable(value = "id") Long serieId)
            throws ResourceNotFoundException {
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("Serie not found for this id :: " + serieId));
        return ResponseEntity.ok().body(serie);
    }

    @PostMapping("/series")
    public Serie createSerie(@Valid @RequestBody Serie serie) {
        return serieRepository.save(serie);
    }

    @PutMapping("/series/{id}")
    public ResponseEntity<Serie> updateSerie(@PathVariable(value = "id") Long serieId,
                                           @Valid @RequestBody Serie serieDetails) throws ResourceNotFoundException, ParseException {
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("Serie not found for this id :: " + serieId));

        serie.setName(serieDetails.getName());
        serie.setAffiche(serieDetails.getAffiche());
        serie.setDuree(serieDetails.getDuree());
        serie.setEpisode(serieDetails.getEpisode());
        serie.setNote(serieDetails.getNote());
        serie.setSaison(serieDetails.getSaison());
        serie.setStatus(serieDetails.getStatus());
        serie.setSynopsis(serieDetails.getSynopsis());
        final Serie updatedserie = serieRepository.save(serie);
        return ResponseEntity.ok(updatedserie);
    }

    @DeleteMapping("/series/{id}")
    public Map<String, Boolean> deleteSerie(@PathVariable(value = "id") Long serieId)
            throws ResourceNotFoundException {
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("Serie not found for this id :: " + serieId));

        serieRepository.delete(serie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
