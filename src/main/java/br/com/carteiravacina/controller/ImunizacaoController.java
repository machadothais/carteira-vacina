package br.com.carteiravacina.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import br.com.carteiravacina.model.Imunizacao;
import br.com.carteiravacina.service.ImunizacaoService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imunizacoes")
public class ImunizacaoController {

    private final ImunizacaoService imunizacaoService;

    public ImunizacaoController(ImunizacaoService imunizacaoService) {
        this.imunizacaoService = imunizacaoService;
    }

    // ✅ Criar imunização
    @PostMapping
    public ResponseEntity<Imunizacao> criar(@RequestBody Imunizacao imunizacao) {
        Imunizacao criada = imunizacaoService.criarImunizacao(imunizacao);
        return ResponseEntity.status(201).body(criada);
    }

    // ✅ Listar todas
    @GetMapping
    public ResponseEntity<List<Imunizacao>> listar() {
        return ResponseEntity.ok(imunizacaoService.listarTodos());
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Imunizacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(imunizacaoService.buscarPorId(id));
    }

    // ✅ Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Imunizacao> atualizar(
            @PathVariable Long id,
            @RequestBody Imunizacao imunizacao
    ) {
        return ResponseEntity.ok(imunizacaoService.alteraImunizacao(id, imunizacao));
    }

    // ✅ Excluir por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluir(@PathVariable Long id) {
        imunizacaoService.excluirImunizacao(id);
        return ResponseEntity.ok(Map.of("mensagem", "Imunização excluída com sucesso"));
    }

    // ✅ Excluir todas de um paciente
    @DeleteMapping("/paciente/{idPaciente}")
    public ResponseEntity<Map<String, String>> excluirPorPaciente(
            @PathVariable Long idPaciente
    ) {
        imunizacaoService.excluirTodasImunizacaoPorPaciente(idPaciente);
        return ResponseEntity.ok(Map.of("mensagem", "Imunizações do paciente excluídas"));
    }

    // ✅ Buscar por paciente
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Imunizacao>> buscarPorPaciente(
            @PathVariable Long idPaciente
    ) {
        return ResponseEntity.ok(imunizacaoService.buscarPorPaciente(idPaciente));
    }

    // ✅ Buscar por paciente e intervalo de datas
    @GetMapping("/paciente/{idPaciente}/aplicacao")
    public ResponseEntity<List<Imunizacao>> buscarPorPacienteEPeriodo(
            @PathVariable Long idPaciente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtIni,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtFim
    ) {
        return ResponseEntity.ok(
                imunizacaoService.buscarPorIdPacienteAndIntervaloAplicacao(idPaciente, dtIni, dtFim)
        );
    }
}