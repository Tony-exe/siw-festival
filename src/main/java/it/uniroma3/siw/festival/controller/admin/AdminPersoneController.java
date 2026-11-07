package it.uniroma3.siw.festival.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.festival.model.Attore;
import it.uniroma3.siw.festival.model.Regista;
import it.uniroma3.siw.festival.service.AttoreService;
import it.uniroma3.siw.festival.service.RegistaService;
import jakarta.validation.Valid;

/**
 * Registi e attori: stessi attributi, quindi stessi template (il form si chiama "persona").
 * Gli attributi "titolo" e "base" dicono al template quale dei due sta gestendo.
 */
@Controller
public class AdminPersoneController {

    private static final String REGISTI = "/admin/registi";
    private static final String ATTORI = "/admin/attori";

    private final RegistaService registaService;
    private final AttoreService attoreService;

    public AdminPersoneController(RegistaService registaService, AttoreService attoreService) {
        this.registaService = registaService;
        this.attoreService = attoreService;
    }

    // --- Registi ---

    @GetMapping(REGISTI)
    public String registi(Model model) {
        model.addAttribute("persone", registaService.elenco());
        return elenco("Registi", REGISTI, model);
    }

    @GetMapping(REGISTI + "/new")
    public String nuovoRegista(Model model) {
        model.addAttribute("persona", new Regista());
        return form("Nuovo regista", REGISTI, null, model);
    }

    @PostMapping(REGISTI)
    public String creaRegista(@Valid @ModelAttribute("persona") Regista persona, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("Nuovo regista", REGISTI, null, model);
        }
        registaService.crea(persona);
        redirectAttributes.addFlashAttribute("successo", "Regista inserito");
        return "redirect:" + REGISTI;
    }

    @GetMapping(REGISTI + "/{id}/edit")
    public String modificaRegistaForm(@PathVariable Long id, Model model) {
        model.addAttribute("persona", registaService.trova(id));
        return form("Modifica regista", REGISTI, id, model);
    }

    @PostMapping(REGISTI + "/{id}")
    public String modificaRegista(@PathVariable Long id, @Valid @ModelAttribute("persona") Regista persona,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("Modifica regista", REGISTI, id, model);
        }
        registaService.modifica(id, persona);
        redirectAttributes.addFlashAttribute("successo", "Regista aggiornato");
        return "redirect:" + REGISTI;
    }

    // --- Attori ---

    @GetMapping(ATTORI)
    public String attori(Model model) {
        model.addAttribute("persone", attoreService.elenco());
        return elenco("Attori", ATTORI, model);
    }

    @GetMapping(ATTORI + "/new")
    public String nuovoAttore(Model model) {
        model.addAttribute("persona", new Attore());
        return form("Nuovo attore", ATTORI, null, model);
    }

    @PostMapping(ATTORI)
    public String creaAttore(@Valid @ModelAttribute("persona") Attore persona, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("Nuovo attore", ATTORI, null, model);
        }
        attoreService.crea(persona);
        redirectAttributes.addFlashAttribute("successo", "Attore inserito");
        return "redirect:" + ATTORI;
    }

    @GetMapping(ATTORI + "/{id}/edit")
    public String modificaAttoreForm(@PathVariable Long id, Model model) {
        model.addAttribute("persona", attoreService.trova(id));
        return form("Modifica attore", ATTORI, id, model);
    }

    @PostMapping(ATTORI + "/{id}")
    public String modificaAttore(@PathVariable Long id, @Valid @ModelAttribute("persona") Attore persona,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("Modifica attore", ATTORI, id, model);
        }
        attoreService.modifica(id, persona);
        redirectAttributes.addFlashAttribute("successo", "Attore aggiornato");
        return "redirect:" + ATTORI;
    }

    private String elenco(String titolo, String base, Model model) {
        model.addAttribute("titolo", titolo);
        model.addAttribute("base", base);
        return "admin/persone/list";
    }

    private String form(String titolo, String base, Long id, Model model) {
        model.addAttribute("titolo", titolo);
        model.addAttribute("base", base);
        model.addAttribute("personaId", id);
        return "admin/persone/form";
    }
}
