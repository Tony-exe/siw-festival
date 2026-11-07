package it.uniroma3.siw.festival.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.exception.OperazioneNonAutorizzataException;
import it.uniroma3.siw.festival.exception.RecensioneDuplicataException;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.model.Recensione;
import it.uniroma3.siw.festival.model.Utente;
import it.uniroma3.siw.festival.repository.FilmRepository;
import it.uniroma3.siw.festival.repository.RecensioneRepository;

/*
 * Recensioni. L'utente arriva dal controller, che lo ricava dall'utente autenticato
 * (mai da un parametro della richiesta): il controllo di proprietà è qui, non nella UI.
 */
@Service
public class RecensioneService {

    private static final Logger log = LoggerFactory.getLogger(RecensioneService.class);

    private final RecensioneRepository recensioneRepository;
    private final FilmRepository filmRepository;

    public RecensioneService(RecensioneRepository recensioneRepository, FilmRepository filmRepository) {
        this.recensioneRepository = recensioneRepository;
        this.filmRepository = filmRepository;
    }

    @Transactional(readOnly = true)
    public List<Recensione> recensioniDelFilm(Long filmId) {
        verificaFilmEsistente(filmId);
        return recensioneRepository.findByFilmIdWithUtente(filmId);
    }

    /** Una recensione con autore e film caricati. */
    @Transactional(readOnly = true)
    public Recensione trova(Long id) {
        return recensioneRepository.findByIdWithUtenteAndFilm(id)
                .orElseThrow(() -> new EntityNotFoundException("Recensione", id));
    }

    /** Media dei voti (null se non ci sono recensioni). */
    @Transactional(readOnly = true)
    public Double mediaVoti(Long filmId) {
        verificaFilmEsistente(filmId);
        return recensioneRepository.mediaVoti(filmId);
    }

    /*
     * Si controlla prima con una query (messaggio chiaro); la garanzia finale è il vincolo unique
     * (film_id, utente_id) nel database, anche con due richieste concorrenti dello stesso utente.
     */
    @Transactional
    public Recensione crea(Long filmId, Recensione dati, Utente autore) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new EntityNotFoundException("Film", filmId));
        if (recensioneRepository.existsByFilmIdAndUtenteId(filmId, autore.getId())) {
            log.warn("Recensione duplicata rifiutata: film={}, utente={}", filmId, autore.getUsername());
            throw new RecensioneDuplicataException("Hai già recensito \"" + film.getTitolo() + "\"");
        }
        Recensione recensione = new Recensione(dati.getTesto(), dati.getVoto(), LocalDate.now(), film, autore);
        Recensione salvata = recensioneRepository.save(recensione);
        log.info("Recensione inserita: id={}, film={}, utente={}", salvata.getId(), filmId, autore.getUsername());
        return salvata;
    }

    @Transactional
    public Recensione modifica(Long recensioneId, Recensione dati, Utente utente) {
        Recensione recensione = trovaPropria(recensioneId, utente);
        recensione.setTesto(dati.getTesto());
        recensione.setVoto(dati.getVoto());
        recensione.setData(LocalDate.now());
        log.info("Recensione modificata: id={}, utente={}", recensioneId, utente.getUsername());
        return recensione;
    }

    @Transactional
    public void elimina(Long recensioneId, Utente utente) {
        Recensione recensione = trovaPropria(recensioneId, utente);
        recensioneRepository.delete(recensione);
        log.info("Recensione eliminata: id={}, utente={}", recensioneId, utente.getUsername());
    }

    /*
     * La recensione deve esistere (404) e l'autore deve essere l'utente autenticato (403).
     * Pubblico perché lo usa anche il form di modifica: chi non è l'autore non vede nemmeno il form.
     */
    @Transactional(readOnly = true)
    public Recensione trovaPropria(Long recensioneId, Utente utente) {
        Recensione recensione = recensioneRepository.findByIdWithUtenteAndFilm(recensioneId)
                .orElseThrow(() -> new EntityNotFoundException("Recensione", recensioneId));
        if (!recensione.getUtente().equals(utente)) {
            log.warn("Tentativo di modificare la recensione {} di un altro utente da parte di {}", recensioneId,
                    utente.getUsername());
            throw new OperazioneNonAutorizzataException("Puoi modificare o eliminare solo le tue recensioni");
        }
        return recensione;
    }

    private void verificaFilmEsistente(Long filmId) {
        if (!filmRepository.existsById(filmId)) {
            throw new EntityNotFoundException("Film", filmId);
        }
    }
}
