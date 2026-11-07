package it.uniroma3.siw.festival.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.model.Attore;
import it.uniroma3.siw.festival.repository.AttoreRepository;

@Service
public class AttoreService {

    private final AttoreRepository attoreRepository;

    public AttoreService(AttoreRepository attoreRepository) {
        this.attoreRepository = attoreRepository;
    }

    @Transactional(readOnly = true)
    public List<Attore> elenco() {
        return attoreRepository.findAllByOrderByCognomeAscNomeAsc();
    }

    @Transactional(readOnly = true)
    public Attore trova(Long id) {
        return attoreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attore", id));
    }

    @Transactional
    public Attore crea(Attore attore) {
        attore.setId(null);
        return attoreRepository.save(attore);
    }

    @Transactional
    public Attore modifica(Long id, Attore dati) {
        Attore attore = trova(id);
        attore.setNome(dati.getNome());
        attore.setCognome(dati.getCognome());
        attore.setDataNascita(dati.getDataNascita());
        attore.setNazionalita(dati.getNazionalita());
        return attore;
    }

}
