package br.com.carteiravacina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carteiravacina.model.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}