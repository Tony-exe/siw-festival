package it.uniroma3.siw.festival.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import it.uniroma3.siw.festival.exception.ConflittoProgrammazioneException;
import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.exception.OperazioneNonAutorizzataException;
import it.uniroma3.siw.festival.exception.RecensioneDuplicataException;
import it.uniroma3.siw.festival.exception.RegolaBusinessException;

/**
 * Errori dei @RestController, sempre in JSON: {"detail": "...", "errori": [{"campo": "...", "messaggio": "..."}]}
 * (React legge "detail" e, per i 400 di validazione, "errori"). Vale solo per questo package e ha la
 * precedenza su GlobalExceptionHandler, che invece risponde con pagine HTML.
 */
@RestControllerAdvice(basePackageClasses = RestExceptionHandler.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> nonTrovato(EntityNotFoundException e) {
        return risposta(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(OperazioneNonAutorizzataException.class)
    public ResponseEntity<Map<String, Object>> nonAutorizzato(OperazioneNonAutorizzataException e) {
        return risposta(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler({ RecensioneDuplicataException.class, ConflittoProgrammazioneException.class })
    public ResponseEntity<Map<String, Object>> conflitto(RuntimeException e) {
        return risposta(HttpStatus.CONFLICT, e.getMessage());
    }

    // Transazione SERIALIZABLE annullata da PostgreSQL per un conflitto con un'altra concorrente: si può riprovare
    @ExceptionHandler(ConcurrencyFailureException.class)
    public ResponseEntity<Map<String, Object>> concorrenza(ConcurrencyFailureException e) {
        return risposta(HttpStatus.CONFLICT,
                "Un'altra operazione ha modificato gli stessi dati nello stesso momento. Riprova.");
    }

    // Vincolo del DB violato (es. recensione duplicata inserita in contemporanea)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> vincoloViolato(DataIntegrityViolationException e) {
        return risposta(HttpStatus.CONFLICT, "L'operazione viola un vincolo di integrità dei dati");
    }

    @ExceptionHandler(RegolaBusinessException.class)
    public ResponseEntity<Map<String, Object>> regolaViolata(RegolaBusinessException e) {
        return risposta(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Map<String, Object>> richiestaNonValida(Exception e) {
        return risposta(HttpStatus.BAD_REQUEST, "Richiesta non valida");
    }

    // @Valid sul corpo JSON non rispettato: 400 con l'elenco dei campi sbagliati
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> datiNonValidi(MethodArgumentNotValidException e) {
        List<Map<String, String>> errori = e.getBindingResult().getFieldErrors().stream()
                .map(errore -> Map.of("campo", errore.getField(),
                        "messaggio", messageSource.getMessage(errore, LocaleContextHolder.getLocale())))
                .toList();
        return ResponseEntity.badRequest().body(Map.of("detail", "Alcuni campi non sono validi", "errori", errori));
    }

    private ResponseEntity<Map<String, Object>> risposta(HttpStatus status, String dettaglio) {
        return ResponseEntity.status(status).body(Map.of("detail", dettaglio));
    }
}
