package br.com.carteiravacina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carteiravacina.model.Dose;
import br.com.carteiravacina.model.Vacina;
import br.com.carteiravacina.repository.DoseRepository;
import br.com.carteiravacina.repository.VacinaRepository;

@Service
public class DoseService {

    @Autowired
    private DoseRepository doseRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    public Dose salvar(Dose dose) {

        if (dose.getVacina() == null) {
            throw new RuntimeException("Dose precisa de vacina");
        }

        Long idVacina = dose.getVacina().getIdVacina();

        Vacina vacina = vacinaRepository.findById(idVacina)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));

        dose.setVacina(vacina);

        return doseRepository.save(dose);
    }

    public List<Dose> listarTodas() {
        return doseRepository.findAll();
    }

    public List<Dose> listarPorVacina(Long idVacina) {
        return doseRepository.findByVacina_IdVacina(idVacina);
    }
}