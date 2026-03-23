package br.com.carteiravacina.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carteiravacina.model.Imunizacao;
import br.com.carteiravacina.service.ImunizacaoService;

@RestController
@RequestMapping("imunizacao")
public class ImunizacaoController {

    private final ImunizacaoService imunizacaoService;

    ImunizacaoController(ImunizacaoService imunizacaoService){
        this.imunizacaoService = imunizacaoService;
    }

    // INSERIR
    @PostMapping("/inserir")
    public ResponseEntity<?> criar(@RequestBody Imunizacao imunizacao){
       
       try {
           // retorna o status 201 created - imunização criada.
            Imunizacao imunizacaoCriada = imunizacaoService.criarImunizacao(imunizacao);
           return ResponseEntity.status(HttpStatus.CREATED).body(imunizacaoCriada);
        
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    // CONSULTAR TODOS
    @GetMapping("/consultar")
    public ResponseEntity<?> consultar(){
        try {

            List<Imunizacao> listarTodas = imunizacaoService.listarTodos();
            // Tudo certo, retorna a lista de imunização.
            return ResponseEntity.ok(listarTodas);
        } catch (Exception e) {
            // Se der algum erro, cai aqui
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("consultar/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable("id") Integer id){
        try {
            Imunizacao consultarId = imunizacaoService.buscarPorId(id);

           return ResponseEntity.ok(consultarId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ATUALIZAR
    @PutMapping("/alterar/{id}")
    public ResponseEntity <?> alterar(@PathVariable("id") Integer id, @RequestBody Imunizacao imunizacao){
        try {
            Imunizacao alterarImunizacao = imunizacaoService.alteraImunizacao(id, imunizacao);
            return ResponseEntity.ok(alterarImunizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETAR
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        try {
            String deletarImunizacaoId = imunizacaoService.excluirImunização(id);
            return ResponseEntity.ok(deletarImunizacaoId);
        } catch (Exception e) {
             String msg = e.getMessage();
				// Como aqui pode ocorrer um erro de SQL, retornamos http status 409 Conflict	que é o status 
				// mais adequado para indicar que houve um conflito no servidor (no caso, um erro de banco de dados)
				// e também retornamos uma mensagem de erro no corpo da resposta. 
				// Como o 409 não é um status de erro genérico, o spring nao possui um método específico para retornar esse status, 
				// por isso usamos o método genérico status() e passamos o HttpStatus.CONFLICT como argumento, e depois usamos o método body() 
				// para colocar a mensagem de erro no corpo da resposta, gerando um JSON com a chave "erro" e o valor da mensagem de erro conforme o 
				// exemplo abaixo:
				// {
				//   "erro": "mensagem de erro aqui"
				// }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", msg));
 
        }
    }

    // DELETAR POR ID PACIENTE
    @DeleteMapping("/excluir/paciente/{id_paciente}")
    public ResponseEntity<?> deletePorIdPaciente(@PathVariable("id_paciente") Long id_paciente){
        try {
            String deletarPorIdPaceinte = imunizacaoService.excluirTodasImunizacaoPorPaciente(id_paciente);
            return ResponseEntity.ok(deletarPorIdPaceinte);
        } catch (Exception e) {
             String msg = e.getMessage();
				
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", msg));
        }
    }

    // CONSULTAR POR ID PACIENTE
    @GetMapping("/consultar/paciente/{id}")
    public ResponseEntity<?> consultarPorIdPaciente(@PathVariable("id") Integer id){
         try {
            Imunizacao consultarIdPaciente = imunizacaoService.buscarPorId(id);

           return ResponseEntity.ok(consultarIdPaciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // CONSULTAR POR ID PACIENTE EM INTERVALO DE TEMPO
    @GetMapping("/consultar/paciente/{id}/aplicacao/{dt_ini}/{dt_fim}")
    public ResponseEntity<?> consultarIdPacienteIntervaloAplicacao(
        @PathVariable("id")Long id,
        @PathVariable("dt_ini")  LocalDate dt_ini,
        @PathVariable("dt_fim")  LocalDate dt_fim
        ){
         try {
            List<Imunizacao> consultarIdPacienteIntervaloAplicacao = imunizacaoService.buscarPorIdPacienteandIntervaloAplicacao(id, dt_ini, dt_fim);

           return ResponseEntity.ok(consultarIdPacienteIntervaloAplicacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
