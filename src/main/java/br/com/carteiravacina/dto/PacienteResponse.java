package br.com.carteiravacina.DTO;

import br.com.carteiravacina.model.enums.Sexo;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteResponse {
    private Long id_paciente;
    private String nome_paciente;
    private Sexo sexo;
    private LocalDate data_nascimento;
    private String cpf;
    private int idade; 
}