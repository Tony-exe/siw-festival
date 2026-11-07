package it.uniroma3.siw.festival.controller.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.festival.model.Festival;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.model.Proiezione;
import it.uniroma3.siw.festival.service.FestivalService;
import it.uniroma3.siw.festival.service.FilmService;
import it.uniroma3.siw.festival.service.ProiezioneService;

@RestController
@RequestMapping("/rest/festivals")
public class FestivalRestController {

    private final FestivalService festivalService;
    private final FilmService filmService;
    private final ProiezioneService proiezioneService;

    public FestivalRestController(FestivalService festivalService, FilmService filmService,
            ProiezioneService proiezioneService) {
        this.festivalService = festivalService;
        this.filmService = filmService;
        this.proiezioneService = proiezioneService;
    }

    @GetMapping
    public List<Festival> festivals() {
        return festivalService.elenco();
    }

    @GetMapping("/{id}")
    public Festival festival(@PathVariable("id") Long id) {
        return festivalService.trova(id);
    }

    // Film partecipanti al festival
    @GetMapping("/{id}/movies")
    public List<Film> films(@PathVariable("id") Long id) {
        return filmService.filmDelFestival(id);
    }

    // Programma delle proiezioni, ordinato per data e ora, con filtro opzionale per giorno
    @GetMapping("/{id}/screenings")
    public List<Proiezione> programma(@PathVariable("id") Long id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return proiezioneService.programma(id, date);
    }
}
