package it.uniroma3.siw.festival.controller.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.festival.model.Utente;
import it.uniroma3.siw.festival.service.UtenteService;

// Dice a React chi è collegato (stessa sessione delle pagine Thymeleaf). Risposta vuota se anonimo.
@RestController
public class MeRestController {

    private final UtenteService utenteService;

    public MeRestController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/rest/me")
    public Utente me(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }
        return utenteService.trova(userDetails.getUsername());
    }
}
