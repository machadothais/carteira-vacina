package br.com.carteiravacina.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carteiravacina.model.Vacina;
import br.com.carteiravacina.service.VacinaService;

@RestController
@RequestMapping("vacina")
public class VacinaController {

    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    // CONSULTAR TODOS
    @GetMapping("/consultar")
     public ResponseEntity<?> consultar() {
        try {
            return ResponseEntity.ok(vacinaService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Consultar por faixa etária
    @GetMapping("/consultar/faixa_etaria/{faixa}")
    public ResponseEntity<?> consultarPorFaixaEtaria(@PathVariable String faixa) {
        try {
            return ResponseEntity.ok(vacinaService.findByPublicoAlvo(faixa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Consultar por idade recomendada
    @GetMapping("/consultar/idade_recomendada/{idade}")
    public ResponseEntity<?> consultarPorIdadeRecomendada(@PathVariable int idade) {
        try {
            return ResponseEntity.ok(vacinaService.findByIdadeRecomendada(idade));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
