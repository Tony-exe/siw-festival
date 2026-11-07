package it.uniroma3.siw.festival.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.exception.RegolaBusinessException;
import it.uniroma3.siw.festival.model.Festival;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.repository.FestivalRepository;
import it.uniroma3.siw.festival.repository.FilmRepository;
import it.uniroma3.siw.festival.repository.ProiezioneRepository;

@Service
public class FestivalService {

    private static final Logger log = LoggerFactory.getLogger(FestivalService.class);

    private final FestivalRepository festivalRepository;
    private final FilmRepository filmRepository;
    private final ProiezioneRepository proiezioneRepository;

    public FestivalService(FestivalRepository festivalRepository, FilmRepository filmRepository,
            ProiezioneRepository proiezioneRepository) {
        this.festivalRepository = festivalRepository;
        this.filmRepository = filmRepository;
        this.proiezioneRepository = proiezioneRepository;
    }

    @Transactional(readOnly = true)
    public List<Festival> elenco() {
        return festivalRepository.findAllByOrderByDataInizioDesc();
    }

    @Transactional(readOnly = true)
    public Festival trova(Long id) {
        return festivalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Festival", id));
    }

    /** Festival con la collezione dei film già caricata (utilizzabile anche fuori dalla transazione). */
    @Transactional(readOnly = true)
    public Festival trovaConFilm(Long id) {
        return festivalRepository.findByIdWithFilms(id)
                .orElseThrow(() -> new EntityNotFoundException("Festival", id));
    }

    @Transactional
    public Festival crea(Festival festival) {
        verificaPeriodo(festival);
        festival.setId(null);
        Festival salvato = festivalRepository.save(festival);
        log.info("Festival creato: id={}, nome={}", salvato.getId(), salvato.getNome());
        return salvato;
    }

    @Transactional
    public Festival modifica(Long id, Festival dati) {
        verificaPeriodo(dati);
        Festival festival = trova(id);
        if (proiezioneRepository.esistonoProiezioniFuoriPeriodo(id, dati.getDataInizio(), dati.getDataFine())) {
            throw new RegolaBusinessException(
                    "Ci sono proiezioni programmate fuori dal nuovo periodo: annullale o spostale prima");
        }
        // Nessuna save: l'entità è "managed", al commit Hibernate rileva le modifiche (dirty checking)
        copiaDati(dati, festival);
        return festival;
    }

    @Transactional
    public void aggiungiFilm(Long festivalId, Long filmId) {
        Festival festival = trovaConFilm(festivalId);
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new EntityNotFoundException("Film", filmId));
        if (festival.getFilms().contains(film)) {
            throw new RegolaBusinessException("Il film partecipa già a questo festival");
        }
        // Festival è il lato proprietario: aggiungere qui produce l'INSERT in festival_film
        festival.addFilm(film);
        log.info("Film {} aggiunto al festival {}", filmId, festivalId);
    }

    /**
     * Rimuove un film da un festival. Regola di business: le proiezioni ancora programmate di quel film
     * in quel festival vengono annullate (CANCELLED, non eliminate, per conservarne lo storico);
     * quelle già svolte (COMPLETED) restano invariate.
     * Tutto avviene nella stessa transazione: se qualcosa fallisce, né l'associazione né le
     * proiezioni vengono modificate.
     *
     * @return il numero di proiezioni annullate
     */
    @Transactional
    public int rimuoviFilm(Long festivalId, Long filmId) {
        Festival festival = trovaConFilm(festivalId);
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new EntityNotFoundException("Film", filmId));
        if (!festival.getFilms().contains(film)) {
            throw new RegolaBusinessException("Il film non partecipa a questo festival");
        }

        festival.removeFilm(film); // DELETE della riga in festival_film al commit

        // un solo UPDATE JPQL per tutte le proiezioni programmate (le COMPLETED restano invariate)
        int annullate = proiezioneRepository.annullaProgrammate(festivalId, filmId);
        log.info("Film {} rimosso dal festival {}: {} proiezioni annullate", filmId, festivalId, annullate);
        return annullate;
    }

    private void verificaPeriodo(Festival festival) {
        if (festival.getDataFine().isBefore(festival.getDataInizio())) {
            throw new RegolaBusinessException("La data di fine non può precedere la data di inizio");
        }
    }

    private void copiaDati(Festival dati, Festival festival) {
        festival.setNome(dati.getNome());
        festival.setAnno(dati.getAnno());
        festival.setCitta(dati.getCitta());
        festival.setDataInizio(dati.getDataInizio());
        festival.setDataFine(dati.getDataFine());
        festival.setDescrizione(dati.getDescrizione());
    }
}
