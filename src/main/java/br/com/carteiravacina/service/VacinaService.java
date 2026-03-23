package br.com.carteiravacina.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.carteiravacina.enumerator.PublicoAlvoEnum;
import br.com.carteiravacina.model.Vacina;
import br.com.carteiravacina.repository.VacinaRepository;

@Service
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

    PublicoAlvoEnum publicoEnum = PublicoAlvoEnum.valueOf(publicoAlvo.toUpperCase());

    Optional<List<Vacina>> resultado =
            Optional.ofNullable(vacinaRepository.findByPublicoAlvo(publicoEnum));

    return resultado.orElse(Collections.emptyList());
}

    public List<Vacina> findByIdadeRecomendada (int idade) {
        
        Optional<List<Vacina>> resultado = Optional.ofNullable(vacinaRepository.findByLimiteAplicacao(idade));
        
        return resultado.orElse(Collections.emptyList());

    }
}
