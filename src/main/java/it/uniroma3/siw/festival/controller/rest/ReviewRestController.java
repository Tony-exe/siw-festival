package it.uniroma3.siw.festival.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.festival.model.Recensione;
import it.uniroma3.siw.festival.service.RecensioneService;
import it.uniroma3.siw.festival.service.UtenteService;
import jakarta.validation.Valid;

// Modifica ed eliminazione di una recensione: solo l'autore (il controllo è nel service, 403 altrimenti)
@RestController
@RequestMapping("/rest/reviews")
public class ReviewRestController {

    private final RecensioneService recensioneService;
    private final UtenteService utenteService;

    public ReviewRestController(RecensioneService recensioneService, UtenteService utenteService) {
        this.recensioneService = recensioneService;
        this.utenteService = utenteService;
    }

    @GetMapping("/{id}")
    public Recensione recensione(@PathVariable("id") Long id) {
        return recensioneService.trova(id);
    }

    @PutMapping("/{id}")
    public Recensione modifica(@PathVariable("id") Long id, @Valid @RequestBody Recensione recensione,
            @AuthenticationPrincipal UserDetails userDetails) {
        return recensioneService.modifica(id, recensione, utenteService.trova(userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void elimina(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        recensioneService.elimina(id, utenteService.trova(userDetails.getUsername()));
    }
}
