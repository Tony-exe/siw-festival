package it.uniroma3.siw.festival.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.festival.model.Recensione;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {

    // Recensioni di un film con l'autore (serve lo username) in un'unica query
    @Query("select r from Recensione r join fetch r.utente where r.film.id = :filmId order by r.data desc, r.id desc")
    List<Recensione> findByFilmIdWithUtente(@Param("filmId") Long filmId);

    // Per modifica/eliminazione: serve l'autore (controllo di proprietà) e il film (risposta all'utente)
    @Query("select r from Recensione r join fetch r.utente join fetch r.film where r.id = :id")
    Optional<Recensione> findByIdWithUtenteAndFilm(@Param("id") Long id);

    boolean existsByFilmIdAndUtenteId(Long filmId, Long utenteId);

    // Media calcolata dal database (null se il film non ha recensioni)
    @Query("select avg(r.voto) from Recensione r where r.film.id = :filmId")
    Double mediaVoti(@Param("filmId") Long filmId);
}
