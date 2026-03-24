package br.com.carteiravacina.controller;

import br.com.carteiravacina.dto.PacienteResponse;
import br.com.carteiravacina.model.enums.Sexo;
import br.com.carteiravacina.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(PacienteController.class)
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PacienteService pacienteService;

    @Test
    void deveListarTodosPacientes() throws Exception {
        PacienteResponse paciente = new PacienteResponse(
                1L,
                "Maria Souza",
                Sexo.FEMININO,
                LocalDate.of(2010, 5, 10),
                "12345678900",
                15
        );

        when(pacienteService.findAll()).thenReturn(List.of(paciente));

        mockMvc.perform(get("/paciente/consultar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_paciente").value(1))
                .andExpect(jsonPath("$[0].nome_paciente").value("Maria Souza"));
    }
}