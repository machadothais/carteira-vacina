package br.com.carteiravacina.repository;

import br.com.carteiravacina.model.Paciente;
import br.com.carteiravacina.model.enums.Sexo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    @DisplayName("Deve salvar paciente com sucesso")
    void deveSalvarPacienteComSucesso() {

        Paciente paciente = Paciente.builder()
        .nome_paciente("João Silva")
        .sexo(Sexo.MASCULINO)
        .data_nascimento(LocalDate.of(2012, 1, 1))
        .cpf("12345678900")
        .build();

        Paciente salvo = pacienteRepository.save(paciente);

        assertNotNull(salvo);
        assertNotNull(salvo.getIdPaciente());
        assertEquals("João Silva", salvo.getNome_paciente());
        assertEquals("12345678900", salvo.getCpf());
    }
}