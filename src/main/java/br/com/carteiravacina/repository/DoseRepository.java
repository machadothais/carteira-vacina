package br.com.carteiravacina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carteiravacina.model.Dose;

public interface DoseRepository extends JpaRepository<Dose, Long> {

    List<Dose> findByIdadeRecomendadaLessThanEqual(int idadeAtualEmMeses);

    List<Dose> findByIdadeRecomendada(int idadeEmMesesProximoMes);

    List<Dose> findByVacinaIdVacina(Long idVacina);
}