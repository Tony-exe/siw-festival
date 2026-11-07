package it.uniroma3.siw.festival.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import it.uniroma3.siw.festival.exception.OperazioneNonAutorizzataException;
import it.uniroma3.siw.festival.exception.RecensioneDuplicataException;
import it.uniroma3.siw.festival.exception.UsernameGiaUsatoException;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.model.Ruolo;
import it.uniroma3.siw.festival.model.Utente;
import it.uniroma3.siw.festival.model.Recensione;
import it.uniroma3.siw.festival.model.Regista;
import it.uniroma3.siw.festival.repository.FilmRepository;
import it.uniroma3.siw.festival.repository.RecensioneRepository;
import it.uniroma3.siw.festival.repository.RegistaRepository;
import it.uniroma3.siw.festival.support.DatabaseCleaner;
import it.uniroma3.siw.festival.repository.UtenteRepository;

/** Servizio recensioni su un DB vero: l'autore è l'Utente passato dal controller. */
@SpringBootTest
@ActiveProfiles("test")
class RecensioneServiceIntegrationTest {

    @Autowired
    private RecensioneService recensioneService;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private RecensioneRepository recensioneRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private RegistaRepository registaRepository;
    @Autowired
    private JdbcTemplate jdbc;

    private Film film;
    private Utente mario;
    private Utente luigi;

    @BeforeEach
    void setUp() {
        Regista regista = registaRepository.save(new Regista("Mario", "Rossi", LocalDate.of(1970, 1, 1), "Italiana"));
        film = filmRepository.save(new Film("Film di prova", 2020, 100, "Drammatico", "Italia", regista));
        mario = utenteService.registra(new Utente("mario", "password", Ruolo.USER));
        luigi = utenteService.registra(new Utente("luigi", "password", Ruolo.USER));
    }

    @AfterEach
    void tearDown() {
        DatabaseCleaner.svuota(jdbc);
    }

    private Recensione dati(String testo, int voto) {
        return new Recensione(testo, voto, LocalDate.now(), null, null);
    }

    // Recensione di Mario inserita direttamente col repository (preparazione del test)
    private Recensione recensioneDiMario() {
        return recensioneRepository.save(new Recensione("Testo originale di Mario", 4, LocalDate.now(), film, mario));
    }

    @Test
    void unaSolaRecensionePerUtentePerFilm() {
        recensioneService.crea(film.getId(), dati("Prima recensione del film", 4), mario);

        assertThatThrownBy(() -> recensioneService.crea(film.getId(), dati("Ci riprovo con un'altra", 1), mario))
                .isInstanceOf(RecensioneDuplicataException.class);
        assertThat(recensioneRepository.count()).isEqualTo(1);
    }

    @Test
    void unAltroUtentePuoRecensireLoStessoFilm() {
        recensioneDiMario();
        recensioneService.crea(film.getId(), dati("Recensione di Luigi", 2), luigi);

        assertThat(recensioneService.recensioniDelFilm(film.getId())).hasSize(2);
        assertThat(recensioneService.mediaVoti(film.getId())).isEqualTo(3.0);
    }

    // Luigi è un utente valido, ma la recensione non è sua → 403 di dominio
    @Test
    void nonSiModificaNeSiEliminaLaRecensioneDiUnAltro() {
        Recensione r = recensioneDiMario();

        assertThatThrownBy(() -> recensioneService.modifica(r.getId(), dati("Testo di Luigi!", 1), luigi))
                .isInstanceOf(OperazioneNonAutorizzataException.class);
        assertThatThrownBy(() -> recensioneService.elimina(r.getId(), luigi))
                .isInstanceOf(OperazioneNonAutorizzataException.class);
        assertThat(recensioneRepository.findById(r.getId()).orElseThrow().getTesto())
                .isEqualTo("Testo originale di Mario");
    }

    @Test
    void lAutoreModificaEdEliminaLaPropria() {
        Recensione r = recensioneDiMario();

        recensioneService.modifica(r.getId(), dati("Testo corretto di Mario", 5), mario);
        assertThat(recensioneRepository.findById(r.getId()).orElseThrow().getVoto()).isEqualTo(5);

        recensioneService.elimina(r.getId(), mario);
        assertThat(recensioneRepository.count()).isZero();
    }

    @Test
    void registrazioneConUsernameGiaUsato() {
        assertThatThrownBy(() -> utenteService.registra(new Utente("mario", "altra", Ruolo.USER)))
                .isInstanceOf(UsernameGiaUsatoException.class);
        assertThat(utenteService.trova("mario").getPassword()).startsWith("$2"); // hash BCrypt
    }
}
