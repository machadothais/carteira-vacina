package br.com.carteiravacina.repository;

import br.com.carteiravacina.enums.Sexo;
import br.com.carteiravacina.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    @DisplayName("Deve salvar paciente com sucesso")
    void deveSalvarPacienteComSucesso() {
        Paciente paciente = new Paciente();
        paciente.setNomePaciente("João Silva");
        paciente.setCpfPaciente("12345678900");
        paciente.setSexo(Sexo.M);
        paciente.setDataNascimento(LocalDate.of(2012, 1, 1));

        Paciente salvo = pacienteRepository.save(paciente);

        assertNotNull(salvo.getId());
    }
}