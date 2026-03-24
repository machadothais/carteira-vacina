package br.com.carteiravacina.repository;

import br.com.carteiravacina.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoseRepository extends JpaRepository<Dose, Long> {

    List<Dose> findByVacina_IdVacina(Long idVacina);

    List<Dose> findByIdadeRecomendada(Integer idadeRecomendada);

    List<Dose> findByIdadeRecomendadaLessThanEqual(Integer idadeRecomendada);
}