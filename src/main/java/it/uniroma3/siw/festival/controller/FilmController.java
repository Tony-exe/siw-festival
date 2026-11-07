package it.uniroma3.siw.festival.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.festival.exception.RecensioneDuplicataException;
import it.uniroma3.siw.festival.model.Recensione;
import it.uniroma3.siw.festival.service.FilmService;
import it.uniroma3.siw.festival.service.RecensioneService;
import it.uniroma3.siw.festival.service.UtenteService;
import jakarta.validation.Valid;

/**
 * Dettaglio di un film e gestione delle recensioni (pagine Thymeleaf).
 * La pagina è pubblica; i POST richiedono il login (regola anyRequest().authenticated() in AuthConfig).
 * L'autore di una recensione è sempre l'utente autenticato (@AuthenticationPrincipal), mai un campo del form,
 * e il controllo "solo l'autore modifica o elimina" è nel service (403 altrimenti).
 */
@Controller
public class FilmController {

    private final FilmService filmService;
    private final RecensioneService recensioneService;
    private final UtenteService utenteService;

    public FilmController(FilmService filmService, RecensioneService recensioneService,
            UtenteService utenteService) {
        this.filmService = filmService;
        this.recensioneService = recensioneService;
        this.utenteService = utenteService;
    }

    @GetMapping("/films/{id}")
    public String dettaglio(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("recensione", new Recensione()); // form object (vuoto) per la nuova recensione
        caricaDettaglio(id, userDetails, model);
        return "films/show";
    }

    @PostMapping("/films/{id}/reviews")
    public String creaRecensione(@PathVariable Long id, @Valid @ModelAttribute("recensione") Recensione recensione,
            BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model,
            RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            try {
                recensioneService.crea(id, recensione, utenteService.trova(userDetails.getUsername()));
                // Post/Redirect/Get: ricaricare la pagina non re-invia la recensione
                redirectAttributes.addFlashAttribute("successo", "Recensione pubblicata");
                return "redirect:/films/" + id;
            } catch (RecensioneDuplicataException e) {
                // errore di business mostrato nel form, come un errore di validazione
                bindingResult.reject("duplicata", e.getMessage());
            }
        }
        // dati non validi: rimostro la pagina con gli errori accanto ai campi
        caricaDettaglio(id, userDetails, model);
        return "films/show";
    }

    @GetMapping("/films/{filmId}/reviews/{id}/edit")
    public String modificaForm(@PathVariable Long filmId, @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails, Model model) {
        // 404 se non esiste, 403 se non è dell'utente (GlobalExceptionHandler)
        Recensione recensione = recensioneService.trovaPropria(id, utenteService.trova(userDetails.getUsername()));
        model.addAttribute("recensione", recensione);
        model.addAttribute("film", recensione.getFilm());
        return "films/review-form";
    }

    @PostMapping("/films/{filmId}/reviews/{id}")
    public String modificaRecensione(@PathVariable Long filmId, @PathVariable Long id,
            @Valid @ModelAttribute("recensione") Recensione recensione, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            recensione.setId(id); // l'id serve al th:action del form
            model.addAttribute("film", filmService.trovaConCast(filmId));
            return "films/review-form";
        }
        recensioneService.modifica(id, recensione, utenteService.trova(userDetails.getUsername()));
        redirectAttributes.addFlashAttribute("successo", "Recensione aggiornata");
        return "redirect:/films/" + filmId;
    }

    @PostMapping("/films/{filmId}/reviews/{id}/delete")
    public String eliminaRecensione(@PathVariable Long filmId, @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        recensioneService.elimina(id, utenteService.trova(userDetails.getUsername()));
        redirectAttributes.addFlashAttribute("successo", "Recensione eliminata");
        return "redirect:/films/" + filmId;
    }

    /*
     * Tutto ciò che mostra la pagina, caricato dai service con query dedicate (open-in-view=false:
     * il template non può caricare associazioni LAZY). Usato sia dal GET sia dal POST con errori.
     */
    private void caricaDettaglio(Long id, UserDetails userDetails, Model model) {
        List<Recensione> recensioni = recensioneService.recensioniDelFilm(id);
        model.addAttribute("film", filmService.trovaConCast(id));
        model.addAttribute("festivals", filmService.festivalDelFilm(id));
        model.addAttribute("proiezioni", filmService.proiezioniDelFilm(id));
        model.addAttribute("recensioni", recensioni);
        model.addAttribute("mediaVoti", recensioneService.mediaVoti(id));
        // il form di inserimento si mostra solo a chi non ha ancora recensito il film
        boolean giaRecensito = userDetails != null && recensioni.stream()
                .anyMatch(r -> r.getUtente().getUsername().equals(userDetails.getUsername()));
        model.addAttribute("giaRecensito", giaRecensito);
    }
}
