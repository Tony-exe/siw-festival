package it.uniroma3.siw.festival.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.festival.exception.RegolaBusinessException;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.service.AttoreService;
import it.uniroma3.siw.festival.service.FilmService;
import it.uniroma3.siw.festival.service.RegistaService;
import jakarta.validation.Valid;

/** Inserimento e modifica dei film, con regista e cast. */
@Controller
@RequestMapping("/admin/films")
public class AdminFilmController {

    private final FilmService filmService;
    private final RegistaService registaService;
    private final AttoreService attoreService;

    public AdminFilmController(FilmService filmService, RegistaService registaService,
            AttoreService attoreService) {
        this.filmService = filmService;
        this.registaService = registaService;
        this.attoreService = attoreService;
    }

    @GetMapping
    public String elenco(Model model) {
        model.addAttribute("films", filmService.elenco());
        return "admin/films/list";
    }

    @GetMapping("/new")
    public String nuovo(Model model) {
        model.addAttribute("film", new Film());
        return mostraForm(null, null, model);
    }

    @PostMapping
    public String crea(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult,
            @RequestParam(value = "registaId", required = false) Long registaId, Model model,
            RedirectAttributes redirectAttributes) {
        controllaRegista(registaId, bindingResult);
        if (bindingResult.hasErrors()) {
            return mostraForm(null, registaId, model);
        }
        Film salvato = filmService.crea(film, registaId);
        redirectAttributes.addFlashAttribute("successo", "Film \"" + salvato.getTitolo() + "\" creato: ora puoi inserire il cast");
        return "redirect:/admin/films/" + salvato.getId() + "/cast";
    }

    @GetMapping("/{id}/edit")
    public String modificaForm(@PathVariable Long id, Model model) {
        Film film = filmService.trovaConCast(id);
        model.addAttribute("film", film);
        return mostraForm(id, film.getRegista().getId(), model);
    }

    @PostMapping("/{id}")
    public String modifica(@PathVariable Long id, @Valid @ModelAttribute("film") Film film,
            BindingResult bindingResult, @RequestParam(value = "registaId", required = false) Long registaId,
            Model model, RedirectAttributes redirectAttributes) {
        controllaRegista(registaId, bindingResult);
        if (bindingResult.hasErrors()) {
            return mostraForm(id, registaId, model);
        }
        try {
            filmService.modifica(id, film, registaId);
        } catch (RegolaBusinessException e) {
            bindingResult.reject("regola", e.getMessage());
            return mostraForm(id, registaId, model);
        }
        redirectAttributes.addFlashAttribute("successo", "Film aggiornato");
        return "redirect:/admin/films";
    }

    // Il form ha bisogno anche dell'elenco dei registi per la select
    private String mostraForm(Long filmId, Long registaId, Model model) {
        model.addAttribute("filmId", filmId);
        model.addAttribute("registaId", registaId);
        model.addAttribute("registi", registaService.elenco());
        return "admin/films/form";
    }

    // --- Cast ---

    @GetMapping("/{id}/cast")
    public String cast(@PathVariable Long id, Model model) {
        return mostraCast(id, model);
    }

    @PostMapping("/{id}/cast")
    public String aggiungiInterpretazione(@PathVariable Long id,
            @RequestParam(value = "attoreId", required = false) Long attoreId,
            @RequestParam(value = "personaggio", required = false) String personaggio, Model model,
            RedirectAttributes redirectAttributes) {
        if (attoreId == null) {
            model.addAttribute("errore", "Seleziona un attore");
            return mostraCast(id, model);
        }
        try {
            filmService.aggiungiInterpretazione(id, attoreId, personaggio);
        } catch (RegolaBusinessException e) {
            model.addAttribute("errore", e.getMessage());
            return mostraCast(id, model);
        }
        redirectAttributes.addFlashAttribute("successo", "Interpretazione aggiunta");
        return "redirect:/admin/films/" + id + "/cast";
    }

    @PostMapping("/{id}/cast/{interpretazioneId}/remove")
    public String rimuoviInterpretazione(@PathVariable Long id, @PathVariable Long interpretazioneId,
            RedirectAttributes redirectAttributes) {
        filmService.rimuoviInterpretazione(id, interpretazioneId);
        redirectAttributes.addFlashAttribute("successo", "Interpretazione rimossa");
        return "redirect:/admin/films/" + id + "/cast";
    }

    // Il regista non è un campo del form (arriva da una select): se manca segnalo l'errore sul campo
    private void controllaRegista(Long registaId, BindingResult bindingResult) {
        if (registaId == null) {
            bindingResult.rejectValue("regista", "NotNull.registaId", "Seleziona un regista");
        }
    }

    private String mostraCast(Long filmId, Model model) {
        model.addAttribute("film", filmService.trovaConCast(filmId)); // regista e cast già caricati
        model.addAttribute("attori", attoreService.elenco());
        return "admin/films/cast";
    }
}
