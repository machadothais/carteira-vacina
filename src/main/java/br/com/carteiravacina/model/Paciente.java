package br.com.carteiravacina.model;

import java.time.LocalDate;

import br.com.carteiravacina.model.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Table(name = "paciente", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(length = 200, nullable = false)
    private String nome_paciente;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Sexo sexo;

    @Column(length = 10, nullable = false)
    private LocalDate data_nascimento;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;
}
