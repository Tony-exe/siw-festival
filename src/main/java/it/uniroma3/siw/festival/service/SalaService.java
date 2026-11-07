package it.uniroma3.siw.festival.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.model.Sala;
import it.uniroma3.siw.festival.repository.SalaRepository;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Transactional(readOnly = true)
    public List<Sala> elenco() {
        return salaRepository.findAllByOrderByNomeAsc();
    }

    @Transactional(readOnly = true)
    public Sala trova(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sala", id));
    }

    @Transactional
    public Sala crea(Sala sala) {
        sala.setId(null);
        return salaRepository.save(sala);
    }

    @Transactional
    public Sala modifica(Long id, Sala dati) {
        Sala sala = trova(id);
        sala.setNome(dati.getNome());
        sala.setIndirizzo(dati.getIndirizzo());
        sala.setCapienza(dati.getCapienza());
        return sala;
    }

}
