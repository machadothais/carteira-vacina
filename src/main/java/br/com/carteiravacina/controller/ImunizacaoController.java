package br.com.carteiravacina.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import br.com.carteiravacina.model.Imunizacao;
import br.com.carteiravacina.service.ImunizacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imunizacao")
public class ImunizacaoController {

    private final ImunizacaoService imunizacaoService;

    public ImunizacaoController(ImunizacaoService imunizacaoService) {
        this.imunizacaoService = imunizacaoService;
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> criar(@RequestBody Imunizacao imunizacao) {
        try {
            Imunizacao imunizacaoCriada = imunizacaoService.criarImunizacao(imunizacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(imunizacaoCriada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultar() {
        try {
            List<Imunizacao> listarTodas = imunizacaoService.listarTodos();
            return ResponseEntity.ok(listarTodas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable("id") Long id) {
        try {
            Imunizacao consultarId = imunizacaoService.buscarPorId(id);
            return ResponseEntity.ok(consultarId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody Imunizacao imunizacao) {
        try {
            Imunizacao alterarImunizacao = imunizacaoService.alteraImunizacao(id, imunizacao);
            return ResponseEntity.ok(alterarImunizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            String deletarImunizacaoId = imunizacaoService.excluirImunizacao(id);
            return ResponseEntity.ok(Map.of("mensagem", deletarImunizacaoId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping("/excluir/paciente/{idPaciente}")
    public ResponseEntity<?> deletePorIdPaciente(@PathVariable("idPaciente") Long idPaciente) {
        try {
            String deletarPorIdPaciente = imunizacaoService.excluirTodasImunizacaoPorPaciente(idPaciente);
            return ResponseEntity.ok(Map.of("mensagem", deletarPorIdPaciente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/consultar/paciente/{idPaciente}")
    public ResponseEntity<?> consultarPorIdPaciente(@PathVariable("idPaciente") Long idPaciente) {
        try {
            List<Imunizacao> consultarIdPaciente = imunizacaoService.buscarPorPaciente(idPaciente);
            return ResponseEntity.ok(consultarIdPaciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/consultar/paciente/{idPaciente}/aplicacao/{dtIni}/{dtFim}")
    public ResponseEntity<?> consultarIdPacienteIntervaloAplicacao(
            @PathVariable("idPaciente") Long idPaciente,
            @PathVariable("dtIni") LocalDate dtIni,
            @PathVariable("dtFim") LocalDate dtFim
    ) {
        try {
            List<Imunizacao> consultarIdPacienteIntervaloAplicacao =
                    imunizacaoService.buscarPorIdPacienteAndIntervaloAplicacao(idPaciente, dtIni, dtFim);

            return ResponseEntity.ok(consultarIdPacienteIntervaloAplicacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}