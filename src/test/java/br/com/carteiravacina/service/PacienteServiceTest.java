package br.com.carteiravacina.service;

import br.com.carteiravacina.dto.request.PacienteRequestDTO;
import br.com.carteiravacina.dto.response.PacienteResponseDTO;
import br.com.carteiravacina.enums.Sexo;
import br.com.carteiravacina.mapper.PacienteMapper;
import br.com.carteiravacina.model.Paciente;
import br.com.carteiravacina.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private PacienteMapper pacienteMapper;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void deveCadastrarPacienteComSucesso() {
        PacienteRequestDTO requestDTO = new PacienteRequestDTO(
                "Maria Souza",
                "12345678900",
                Sexo.F,
                LocalDate.of(2010, 5, 10)
        );

        Paciente paciente = new Paciente();
        paciente.setNomePaciente("Maria Souza");
        paciente.setCpfPaciente("12345678900");
        paciente.setSexo(Sexo.F);
        paciente.setDataNascimento(LocalDate.of(2010, 5, 10));

        Paciente pacienteSalvo = new Paciente();
        pacienteSalvo.setId(1L);
        pacienteSalvo.setNomePaciente("Maria Souza");
        pacienteSalvo.setCpfPaciente("12345678900");
        pacienteSalvo.setSexo(Sexo.F);
        pacienteSalvo.setDataNascimento(LocalDate.of(2010, 5, 10));

        PacienteResponseDTO responseDTO = new PacienteResponseDTO(
                1L,
                "Maria Souza",
                "12345678900",
                Sexo.F,
                LocalDate.of(2010, 5, 10)
        );

        when(pacienteMapper.toModel(requestDTO)).thenReturn(paciente);
        when(pacienteRepository.save(paciente)).thenReturn(pacienteSalvo);
        when(pacienteMapper.toResponseDTO(pacienteSalvo)).thenReturn(responseDTO);

        PacienteResponseDTO resultado = pacienteService.cadastrar(requestDTO);

        assertEquals(1L, resultado.id());
        assertEquals("Maria Souza", resultado.nomePaciente());
    }
}