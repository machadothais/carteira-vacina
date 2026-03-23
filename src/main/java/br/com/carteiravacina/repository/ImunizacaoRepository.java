package br.com.carteiravacina.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carteiravacina.model.Imunizacao;

public interface ImunizacaoRepository extends JpaRepository<Imunizacao, Integer> {

    List<Imunizacao> findByPacienteIdPaciente(Long idPaciente);

    List<Imunizacao> findByPacienteIdPacienteAndDataAplicacaoBetween(Long idPaciente, LocalDate dataInicio, LocalDate dataFim);

    void deleteByPacienteIdPaciente(Long idPaciente);
}
