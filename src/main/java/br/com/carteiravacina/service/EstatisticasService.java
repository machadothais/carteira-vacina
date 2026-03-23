package br.com.carteiravacina.service;

import br.com.carteiravacina.model.Dose;
import br.com.carteiravacina.model.Paciente;
import br.com.carteiravacina.repository.DoseRepository;
import br.com.carteiravacina.repository.EstatisticasRepository;
import br.com.carteiravacina.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstatisticasService {

    @Autowired
    private EstatisticasRepository estatisticasRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoseRepository doseRepository;

    // 1. Qtde de vacinas aplicadas por paciente
    public Long contarVacinasAplicadas(Long idPaciente) {
        return estatisticasRepository.countByPacienteId(idPaciente);
    }

    // 2. Qtde de vacinas aplicáveis no próximo mês
    public Long contarVacinasProximoMes(Long idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente).orElseThrow();
        int idadeEmMesesProximoMes = calcularIdadeEmMeses(paciente.getData_nascimento()) + 1;

        // Busca doses recomendadas exatamente para a idade que ele terá no próximo mês
        List<Dose> dosesRecomendadas = doseRepository.findByIdadeRecomendada(idadeEmMesesProximoMes);

        return filtrarDosesNaoAplicadas(idPaciente, dosesRecomendadas);
    }

    // 3. Qtde de vacinas atrasadas
    public Long contarVacinasAtrasadas(Long idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente).orElseThrow();
        int idadeAtualEmMeses = calcularIdadeEmMeses(paciente.getData_nascimento());

        // Busca todas as doses cuja idade recomendada já passou
        List<Dose> todasDosesPassadas = doseRepository.findByIdadeRecomendadaLessThanEqual(idadeAtualEmMeses);

        return filtrarDosesNaoAplicadas(idPaciente, todasDosesPassadas);
    }

    // 4. Qtde de vacinas acima de uma determinada idade
    public Long contarVacinasAcimaDaIdade(Integer meses) {
        return estatisticasRepository.countDosesAcimaIdade(meses);
    }

    // --- Métodos Auxiliares de Lógica ---

    private int calcularIdadeEmMeses(LocalDate dataNascimento) {
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        return (periodo.getYears() * 12) + periodo.getMonths();
    }

    private Long filtrarDosesNaoAplicadas(Long idPaciente, List<Dose> dosesInteresse) {
        // Pega o ID das doses que o paciente JÁ tomou
        List<Long> idsDosesAplicadas = estatisticasRepository.findByPacienteIdPaciente(idPaciente)
                .stream()
                .map(imunizacao -> imunizacao.getDose().getId())
                .collect(Collectors.toList());

        // Conta quantas das doses de interesse NÃO estão na lista das aplicadas
        return dosesInteresse.stream()
                .filter(dose -> !idsDosesAplicadas.contains(dose.getId()))
                .count();
    }
}