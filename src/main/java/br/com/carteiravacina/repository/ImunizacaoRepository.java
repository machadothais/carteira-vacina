package br.com.carteiravacina.repository;

import br.com.carteiravacina.model.Imunizacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ImunizacaoRepository extends JpaRepository<Imunizacao, Long> {

    List<Imunizacao> findByPaciente_IdPaciente(Long idPaciente);

    List<Imunizacao> findByPaciente_IdPacienteAndDataAplicacaoBetween(
            Long idPaciente,
            LocalDate inicio,
            LocalDate fim
    );

    void deleteByPaciente_IdPaciente(Long idPaciente);

    Long countByPaciente_IdPaciente(Long idPaciente);
}