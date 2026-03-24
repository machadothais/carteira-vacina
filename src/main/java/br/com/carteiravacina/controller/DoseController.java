package br.com.carteiravacina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carteiravacina.model.Dose;
import br.com.carteiravacina.service.DoseService;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    private DoseService doseService;

    @GetMapping("/consultar")
    public ResponseEntity<List<Dose>> listarTodas() {
        return ResponseEntity.ok(doseService.listarTodas());
    }

    @GetMapping("/consultar/vacina/{idVacina}")
    public ResponseEntity<List<Dose>> listarPorVacina(@PathVariable Long idVacina) {
        return ResponseEntity.ok(doseService.listarPorVacina(idVacina));
    }
}