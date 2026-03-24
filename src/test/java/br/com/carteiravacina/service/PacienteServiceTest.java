package br.com.carteiravacina.service;

import br.com.carteiravacina.dto.PacienteDTO;
import br.com.carteiravacina.dto.PacienteResponse;
import br.com.carteiravacina.model.Paciente;
import br.com.carteiravacina.model.enums.Sexo;
import br.com.carteiravacina.repository.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    @DisplayName("Deve salvar paciente com sucesso")
    void deveSalvarPacienteComSucesso() {
        PacienteDTO dto = new PacienteDTO(
                "Maria Souza",
                Sexo.FEMININO,
                LocalDate.of(2010, 5, 10),
                "12345678900"
        );

        Paciente pacienteSalvo = Paciente.builder()
                .idPaciente(1L)
                .nome_paciente("Maria Souza")
                .sexo(Sexo.FEMININO)
                .data_nascimento(LocalDate.of(2010, 5, 10))
                .cpf("12345678900")
                .build();

        when(repository.existsByCpf("12345678900")).thenReturn(false);
        when(repository.save(any(Paciente.class))).thenReturn(pacienteSalvo);

        PacienteResponse resultado = pacienteService.save(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_paciente());
        assertEquals("Maria Souza", resultado.getNome_paciente());
        assertEquals("12345678900", resultado.getCpf());
        assertEquals(Sexo.FEMININO, resultado.getSexo());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF já estiver cadastrado")
    void deveLancarExcecaoQuandoCpfJaExistir() {
        PacienteDTO dto = new PacienteDTO(
                "Maria Souza",
                Sexo.FEMININO,
                LocalDate.of(2010, 5, 10),
                "12345678900"
        );

        when(repository.existsByCpf("12345678900")).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> pacienteService.save(dto)
        );

        assertEquals("CPF já cadastrado: 12345678900", exception.getMessage());
    }
}