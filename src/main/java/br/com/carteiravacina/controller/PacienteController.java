package br.com.carteiravacina.controller;

import br.com.carteiravacina.DTO.PacienteDTO;
import br.com.carteiravacina.DTO.PacienteResponse;
import br.com.carteiravacina.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    // POST
    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@RequestBody PacienteDTO dto) {
        PacienteResponse response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT
    @PutMapping("/alterar/{id}")
    public ResponseEntity<PacienteResponse> alterar(
            @PathVariable Long id,
            @RequestBody @Valid PacienteDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET
    @GetMapping("/consultar")
    public ResponseEntity<List<PacienteResponse>> consultarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET
    @GetMapping("/consultar/{id}")
    public ResponseEntity<PacienteResponse> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}