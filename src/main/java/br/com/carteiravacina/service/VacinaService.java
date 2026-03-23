package br.com.carteiravacina.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.carteiravacina.model.Vacina;
import br.com.carteiravacina.repository.VacinaRepository;

public class VacinaService {

    private final VacinaRepository vacinaRepository;

    public VacinaService(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    public List<Vacina> listarTodos() {
        return this.vacinaRepository.findAll();
    }

    public Vacina criarVacina(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public List<Vacina> findByPublicoAlvo(String publicoAlvo) {

        Optional<List<Vacina>> resultado = Optional.ofNullable(vacinaRepository.findByPublicoAlvo(publicoAlvo));

        return resultado.orElse(Collections.emptyList());
    }

    public List<Vacina> findByIdadeRecomendada (int idade) {
        
        Optional<List<Vacina>> resultado = Optional.ofNullable(vacinaRepository.findByIdadeRecomendada(idade));
        
        return resultado.orElse(Collections.emptyList());

    }
}
