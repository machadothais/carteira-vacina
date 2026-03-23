package br.com.carteiravacina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carteiravacina.enumerator.PublicoAlvoEnum;
import br.com.carteiravacina.model.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    List<Vacina> findByPublicoAlvo(PublicoAlvoEnum publicoEnum);

    List<Vacina> findByLimiteAplicacao(int idade);

}