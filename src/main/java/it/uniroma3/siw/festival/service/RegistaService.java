package it.uniroma3.siw.festival.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.festival.exception.EntityNotFoundException;
import it.uniroma3.siw.festival.model.Regista;
import it.uniroma3.siw.festival.repository.RegistaRepository;

@Service
public class RegistaService {

    private final RegistaRepository registaRepository;

    public RegistaService(RegistaRepository registaRepository) {
        this.registaRepository = registaRepository;
    }

    @Transactional(readOnly = true)
    public List<Regista> elenco() {
        return registaRepository.findAllByOrderByCognomeAscNomeAsc();
    }

    @Transactional(readOnly = true)
    public Regista trova(Long id) {
        return registaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regista", id));
    }

    @Transactional
    public Regista crea(Regista regista) {
        regista.setId(null);
        return registaRepository.save(regista);
    }

    @Transactional
    public Regista modifica(Long id, Regista dati) {
        Regista regista = trova(id);
        regista.setNome(dati.getNome());
        regista.setCognome(dati.getCognome());
        regista.setDataNascita(dati.getDataNascita());
        regista.setNazionalita(dati.getNazionalita());
        return regista;
    }

}
