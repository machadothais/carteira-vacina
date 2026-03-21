package br.com.carteiravacina.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carteiravacina.model.Imunizacao;

public interface ImunizacaoRepository extends JpaRepository<Imunizacao, Integer> {
    List <Imunizacao> findByPacienteId(Integer idPaciente);

    List <Imunizacao> findByPacienteIdAndDataAplicacaoBetween(Integer idPaciente ,LocalDate dataInicio, LocalDate dataFim);

    void deleteByPacienteId(Integer idPaciente);
}
