package it.uniroma3.siw.festival.controller.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.festival.model.Festival;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.model.Interpretazione;
import it.uniroma3.siw.festival.model.Proiezione;
import it.uniroma3.siw.festival.model.Recensione;
import it.uniroma3.siw.festival.service.FilmService;
import it.uniroma3.siw.festival.service.RecensioneService;
import it.uniroma3.siw.festival.service.UtenteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/movies")
public class MovieRestController {

    private final FilmService filmService;
    private final RecensioneService recensioneService;
    private final UtenteService utenteService;

    public MovieRestController(FilmService filmService, RecensioneService recensioneService,
            UtenteService utenteService) {
        this.filmService = filmService;
        this.recensioneService = recensioneService;
        this.utenteService = utenteService;
    }

    // Ricerca film per titolo, genere e regista (filtri opzionali), paginata
    @GetMapping
    public Page<Film> cerca(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return filmService.cerca(title, genre, director, page, size);
    }

    // Film con il suo regista
    @GetMapping("/{id}")
    public Film film(@PathVariable("id") Long id) {
        return filmService.trovaConCast(id);
    }

    @GetMapping("/{id}/cast")
    public List<Interpretazione> cast(@PathVariable("id") Long id) {
        return filmService.cast(id);
    }

    @GetMapping("/{id}/festivals")
    public List<Festival> festivals(@PathVariable("id") Long id) {
        return filmService.festivalDelFilm(id);
    }

    @GetMapping("/{id}/screenings")
    public List<Proiezione> proiezioni(@PathVariable("id") Long id) {
        return filmService.proiezioniDelFilm(id);
    }

    @GetMapping("/{id}/reviews")
    public List<Recensione> recensioni(@PathVariable("id") Long id) {
        return recensioneService.recensioniDelFilm(id);
    }

    // Media dei voti (risposta vuota se il film non ha recensioni)
    @GetMapping("/{id}/reviews/average")
    public Double mediaVoti(@PathVariable("id") Long id) {
        return recensioneService.mediaVoti(id);
    }

    // L'autore è l'utente autenticato, mai un campo del JSON
    @PostMapping("/{id}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public Recensione crea(@PathVariable("id") Long id, @Valid @RequestBody Recensione recensione,
            @AuthenticationPrincipal UserDetails userDetails) {
        return recensioneService.crea(id, recensione, utenteService.trova(userDetails.getUsername()));
    }
}
