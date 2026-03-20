package br.com.carteiravacina.model;

import java.time.LocalDate;

import br.com.carteiravacina.model.enums.Sexo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Table(name = "paciente", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@Data
@Getter
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente;

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
