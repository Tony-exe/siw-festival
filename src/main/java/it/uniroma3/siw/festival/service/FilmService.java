package it.uniroma3.siw.festival.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.exception.RegolaBusinessException;
import it.uniroma3.siw.festival.model.Attore;
import it.uniroma3.siw.festival.model.Festival;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.model.Interpretazione;
import it.uniroma3.siw.festival.model.Proiezione;
import it.uniroma3.siw.festival.model.Regista;
import it.uniroma3.siw.festival.model.StatoProiezione;
import it.uniroma3.siw.festival.repository.AttoreRepository;
import it.uniroma3.siw.festival.repository.FestivalRepository;
import it.uniroma3.siw.festival.repository.FilmRepository;
import it.uniroma3.siw.festival.repository.ProiezioneRepository;
import it.uniroma3.siw.festival.repository.RegistaRepository;

@Service
public class FilmService {

    private static final Logger log = LoggerFactory.getLogger(FilmService.class);

    private static final int DIMENSIONE_MASSIMA_PAGINA = 50;

    private final FilmRepository filmRepository;
    private final RegistaRepository registaRepository;
    private final AttoreRepository attoreRepository;
    private final FestivalRepository festivalRepository;
    private final ProiezioneRepository proiezioneRepository;

    public FilmService(FilmRepository filmRepository, RegistaRepository registaRepository,
            AttoreRepository attoreRepository, FestivalRepository festivalRepository,
            ProiezioneRepository proiezioneRepository) {
        this.filmRepository = filmRepository;
        this.registaRepository = registaRepository;
        this.attoreRepository = attoreRepository;
        this.festivalRepository = festivalRepository;
        this.proiezioneRepository = proiezioneRepository;
    }

    /** Elenco completo con regista (per l'area amministrativa). */
    @Transactional(readOnly = true)
    public List<Film> elenco() {
        return filmRepository.findAllWithRegista();
    }

    /** Ricerca per titolo, genere e regista (filtri opzionali), paginata e ordinata per titolo. */
    @Transactional(readOnly = true)
    public Page<Film> cerca(String titolo, String genere, String regista, int pagina, int dimensione) {
        int size = Math.min(Math.max(dimensione, 1), DIMENSIONE_MASSIMA_PAGINA);
        PageRequest pageable = PageRequest.of(Math.max(pagina, 0), size, Sort.by("titolo"));
        return filmRepository.cerca(vuotoSeNull(titolo), vuotoSeNull(genere), vuotoSeNull(regista), pageable);
    }

    /** Film partecipanti a un festival, con il regista già caricato (JOIN FETCH: 1 query). */
    @Transactional(readOnly = true)
    public List<Film> filmDelFestival(Long festivalId) {
        if (!festivalRepository.existsById(festivalId)) {
            throw new EntityNotFoundException("Festival", festivalId);
        }
        return filmRepository.findByFestivalIdWithRegista(festivalId);
    }

    /** Film che l'amministratore può ancora aggiungere al festival. */
    @Transactional(readOnly = true)
    public List<Film> filmAssociabili(Long festivalId) {
        if (!festivalRepository.existsById(festivalId)) {
            throw new EntityNotFoundException("Festival", festivalId);
        }
        return filmRepository.findNonPartecipantiAlFestival(festivalId);
    }

    /** Film con regista e cast (per la gestione del cast e il form di modifica). */
    @Transactional(readOnly = true)
    public Film trovaConCast(Long id) {
        return filmRepository.findByIdWithRegistaAndCast(id)
                .orElseThrow(() -> new EntityNotFoundException("Film", id));
    }

    /** Cast del film: interpretazioni con l'attore già caricato. */
    @Transactional(readOnly = true)
    public List<Interpretazione> cast(Long id) {
        return trovaConCast(id).getInterpretazioni();
    }

    /** Festival a cui partecipa il film. */
    @Transactional(readOnly = true)
    public List<Festival> festivalDelFilm(Long id) {
        verificaFilmEsistente(id);
        return festivalRepository.findByFilmId(id);
    }

    /** Proiezioni del film con sala, festival e film già caricati. */
    @Transactional(readOnly = true)
    public List<Proiezione> proiezioniDelFilm(Long id) {
        verificaFilmEsistente(id);
        return proiezioneRepository.findByFilmIdWithSalaAndFestival(id);
    }

    @Transactional
    public Film crea(Film film, Long registaId) {
        film.setId(null);
        film.setRegista(trovaRegista(registaId));
        Film salvato = filmRepository.save(film);
        log.info("Film creato: id={}, titolo={}", salvato.getId(), salvato.getTitolo());
        return salvato;
    }

    @Transactional
    public Film modifica(Long id, Film dati, Long registaId) {
        Film film = trovaConCast(id);
        // Le proiezioni programmate hanno oraFine calcolata con la vecchia durata: cambiarla
        // potrebbe creare sovrapposizioni non rilevate, quindi lo impedisco
        if (!Objects.equals(film.getDurata(), dati.getDurata())
                && proiezioneRepository.existsByFilmIdAndStato(id, StatoProiezione.SCHEDULED)) {
            throw new RegolaBusinessException(
                    "Non puoi cambiare la durata di un film con proiezioni programmate");
        }
        film.setTitolo(dati.getTitolo());
        film.setAnno(dati.getAnno());
        film.setDurata(dati.getDurata());
        film.setGenere(dati.getGenere());
        film.setPaeseProduzione(dati.getPaeseProduzione());
        film.setRegista(trovaRegista(registaId));
        return film;
    }

    @Transactional
    public void aggiungiInterpretazione(Long filmId, Long attoreId, String personaggio) {
        Film film = trovaConCast(filmId);
        Attore attore = attoreRepository.findById(attoreId)
                .orElseThrow(() -> new EntityNotFoundException("Attore", attoreId));
        String nome = personaggio == null ? "" : personaggio.trim();
        if (nome.isEmpty()) {
            throw new RegolaBusinessException("Indica il personaggio interpretato");
        }
        boolean giaPresente = film.getInterpretazioni().stream()
                .anyMatch(i -> i.getAttore().equals(attore) && i.getPersonaggio().equalsIgnoreCase(nome));
        if (giaPresente) {
            throw new RegolaBusinessException("Questo attore interpreta già questo personaggio nel film");
        }
        // Grazie a cascade = ALL sul film, la nuova Interpretazione viene salvata al commit
        film.addInterpretazione(attore, nome);
        log.info("Interpretazione aggiunta: film={}, attore={}, personaggio={}", filmId, attoreId, nome);
    }

    @Transactional
    public void rimuoviInterpretazione(Long filmId, Long interpretazioneId) {
        Film film = trovaConCast(filmId);
        Interpretazione interpretazione = film.getInterpretazioni().stream()
                .filter(i -> i.getId().equals(interpretazioneId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Interpretazione", interpretazioneId));
        // orphanRemoval = true: tolta dalla lista, l'interpretazione viene cancellata dal DB
        film.removeInterpretazione(interpretazione);
    }

    private Regista trovaRegista(Long registaId) {
        return registaRepository.findById(registaId)
                .orElseThrow(() -> new EntityNotFoundException("Regista", registaId));
    }

    private void verificaFilmEsistente(Long id) {
        if (!filmRepository.existsById(id)) {
            throw new EntityNotFoundException("Film", id);
        }
    }

    private static String vuotoSeNull(String filtro) {
        return filtro == null ? "" : filtro.trim();
    }
}
