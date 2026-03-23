package br.com.carteiravacina.controller;

import br.com.carteiravacina.dto.response.PacienteResponseDTO;
import br.com.carteiravacina.enums.Sexo;
import br.com.carteiravacina.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PacienteController.class)
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService pacienteService;

    @Test
    void deveListarTodosPacientes() throws Exception {
        PacienteResponseDTO paciente = new PacienteResponseDTO(
                1L,
                "Maria Souza",
                "12345678900",
                Sexo.F,
                LocalDate.of(2010, 5, 10)
        );

        when(pacienteService.listarTodos()).thenReturn(List.of(paciente));

        mockMvc.perform(get("/paciente/consultar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomePaciente").value("Maria Souza"));
    }
}