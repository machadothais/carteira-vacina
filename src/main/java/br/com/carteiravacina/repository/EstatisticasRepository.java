package br.com.carteiravacina.repository;

import br.com.carteiravacina.model.Imunizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstatisticasRepository extends JpaRepository<Imunizacao, Long> {

    @Query("SELECT COUNT(i) FROM Imunizacao i WHERE i.paciente.idPaciente = :id")
    Long countByPacienteId(@Param("id") Long id);

    @Query("SELECT COUNT(d) FROM Dose d WHERE d.idadeRecomendada > :meses")
    Long countDosesAcimaIdade(@Param("meses") Integer meses);

    List<Imunizacao> findByPacienteIdPaciente(Long idPaciente);

}