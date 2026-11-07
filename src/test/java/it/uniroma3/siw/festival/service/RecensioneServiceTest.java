package it.uniroma3.siw.festival.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.exception.OperazioneNonAutorizzataException;
import it.uniroma3.siw.festival.exception.RecensioneDuplicataException;
import it.uniroma3.siw.festival.model.Film;
import it.uniroma3.siw.festival.model.Recensione;
import it.uniroma3.siw.festival.model.Regista;
import it.uniroma3.siw.festival.model.Ruolo;
import it.uniroma3.siw.festival.model.Utente;
import it.uniroma3.siw.festival.repository.FilmRepository;
import it.uniroma3.siw.festival.repository.RecensioneRepository;

@ExtendWith(MockitoExtension.class)
class RecensioneServiceTest {

    @Mock
    private RecensioneRepository recensioneRepository;
    @Mock
    private FilmRepository filmRepository;
    @InjectMocks
    private RecensioneService service;

    private Film film;
    private Utente mario;
    private Recensione recensioneDiMario;
    private final Recensione form = new Recensione("Un film davvero notevole", 4, LocalDate.now(), null, null);
    private Utente luigi;

    @BeforeEach
    void setUp() {
        film = new Film("Film", 2020, 100, "Drammatico", "Italia", new Regista());
        film.setId(1L);
        mario = new Utente("mario", "hash", Ruolo.USER);
        mario.setId(5L);
        luigi = new Utente("luigi", "hash", Ruolo.USER);
        luigi.setId(6L);
        recensioneDiMario = new Recensione("Testo originale della recensione", 3, LocalDate.now(), film, mario);
        recensioneDiMario.setId(7L);

        lenient().when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        lenient().when(recensioneRepository.findByIdWithUtenteAndFilm(7L)).thenReturn(Optional.of(recensioneDiMario));
    }

    @Test
    void secondaRecensioneDelloStessoFilmVieneRifiutata() {
        when(recensioneRepository.existsByFilmIdAndUtenteId(1L, 5L)).thenReturn(true);

        assertThatThrownBy(() -> service.crea(1L, form, mario)).isInstanceOf(RecensioneDuplicataException.class);
        verify(recensioneRepository, never()).save(any());
    }

    @Test
    void creazioneValida() {
        when(recensioneRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Recensione r = service.crea(1L, form, mario);

        assertThat(r.getUtente()).isSameAs(mario);
        assertThat(r.getVoto()).isEqualTo(4);
        assertThat(r.getData()).isEqualTo(LocalDate.now());
    }

    @Test
    void filmInesistente() {
        assertThatThrownBy(() -> service.crea(99L, form, mario)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void autorePuoModificareLaPropriaRecensione() {
        Recensione r = service.modifica(7L, form, mario);

        assertThat(r.getTesto()).isEqualTo("Un film davvero notevole");
        assertThat(r.getVoto()).isEqualTo(4);
    }

    @Test
    void unAltroUtenteNonPuoModificareNeEliminare() {
        assertThatThrownBy(() -> service.modifica(7L, form, luigi))
                .isInstanceOf(OperazioneNonAutorizzataException.class);
        assertThatThrownBy(() -> service.elimina(7L, luigi))
                .isInstanceOf(OperazioneNonAutorizzataException.class);

        assertThat(recensioneDiMario.getTesto()).isEqualTo("Testo originale della recensione");
        verify(recensioneRepository, never()).delete(any());
    }

    @Test
    void recensioneInesistente() {
        assertThatThrownBy(() -> service.elimina(99L, mario)).isInstanceOf(EntityNotFoundException.class);
    }
}
