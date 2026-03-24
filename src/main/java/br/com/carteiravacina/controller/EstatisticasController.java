package br.com.carteiravacina.controller;

import br.com.carteiravacina.service.EstatisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private EstatisticasService estatisticasService;

    // Retorna a quantidade de vacinas aplicadas em um paciente específico
    @GetMapping("/imunizacoes/paciente/{id}")
    public ResponseEntity<?> getQuantidadeVacinasAplicadas(@PathVariable Long id) {
        try {
            Long quantidade = estatisticasService.contarVacinasAplicadas(id);
            return ResponseEntity.ok(quantidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar estatísticas: " + e.getMessage());
        }
    }

    // Retorna a quantidade de vacinas aplicáveis no próximo mês para o paciente
    @GetMapping("/proximas_imunizacoes/paciente/{id}")
    public ResponseEntity<?> getQuantidadeProximasVacinas(@PathVariable Long id) {
        try {
            Long quantidade = estatisticasService.contarVacinasProximoMes(id);
            return ResponseEntity.ok(quantidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao calcular próximas vacinas: " + e.getMessage());
        }
    }

    // Retorna a quantidade de vacinas atrasadas com base na idade atual do paciente
    @GetMapping("/imunizacoes_atrasadas/paciente/{id}")
    public ResponseEntity<?> getQuantidadeVacinasAtrasadas(@PathVariable Long id) {
        try {
            Long quantidade = estatisticasService.contarVacinasAtrasadas(id);
            return ResponseEntity.ok(quantidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao calcular vacinas atrasadas: " + e.getMessage());
        }
    }

    // Retorna a quantidade de vacinas com recomendação acima de uma determinada idade (em meses)
    @GetMapping("/imunizacoes/idade_maior/{meses}")
    public ResponseEntity<?> getQuantidadeVacinasIdadeMaior(@PathVariable Integer meses) {
        try {
            Long quantidade = estatisticasService.contarVacinasAcimaDaIdade(meses);
            return ResponseEntity.ok(quantidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar vacinas por idade: " + e.getMessage());
        }
    }
}
