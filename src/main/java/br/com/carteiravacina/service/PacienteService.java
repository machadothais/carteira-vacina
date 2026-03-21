// br/com/carteiravacina/service/PacienteService.java
package br.com.carteiravacina.service;

import br.com.carteiravacina.DTO.PacienteDTO;
import br.com.carteiravacina.DTO.PacienteResponse;
import br.com.carteiravacina.model.Paciente;
import br.com.carteiravacina.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public List<PacienteResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponse findById(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
        return toResponse(paciente);
    }

    @Transactional
    public PacienteResponse save(PacienteDTO dto) {
        if (repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado: " + dto.getCpf());
        }
        Paciente paciente = toEntity(dto);
        return toResponse(repository.save(paciente));
    }

    @Transactional
    public PacienteResponse update(Long id, PacienteDTO dto) {
        Paciente existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));

        if (repository.existsByCpfAndIdPacienteNot(dto.getCpf(), id)) {
            throw new RuntimeException("CPF já está em uso por outro paciente.");
        }

        existente.setNome_paciente(dto.getNome_paciente());
        existente.setSexo(dto.getSexo());
        existente.setData_nascimento(dto.getData_nascimento());
        existente.setCpf(dto.getCpf());

        return toResponse(repository.save(existente));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // calculo de idade
    public int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private Paciente toEntity(PacienteDTO dto) {
        return Paciente.builder()
                .nome_paciente(dto.getNome_paciente())
                .sexo(dto.getSexo())
                .data_nascimento(dto.getData_nascimento())
                .cpf(dto.getCpf())
                .build();
    }

    private PacienteResponse toResponse(Paciente p) {
        return PacienteResponse.builder()
                .id_paciente(p.getIdPaciente())
                .nome_paciente(p.getNome_paciente())
                .sexo(p.getSexo())
                .data_nascimento(p.getData_nascimento())
                .cpf(p.getCpf())
                .idade(calcularIdade(p.getData_nascimento()))
                .build();
    }
}