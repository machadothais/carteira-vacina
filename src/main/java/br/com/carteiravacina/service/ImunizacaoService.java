package br.com.carteiravacina.service;

import br.com.carteiravacina.model.Imunizacao;
import br.com.carteiravacina.repository.ImunizacaoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ImunizacaoService {
   
    // Litando TODOS
    private final ImunizacaoRepository imunizacaoRepository;

    ImunizacaoService(ImunizacaoRepository imunizacaoRepository) {
        this.imunizacaoRepository = imunizacaoRepository;
    }

    public List<Imunizacao> listarTodos(){
        return this.imunizacaoRepository.findAll();
    } 

    // inserir uma Imunização0
    public Imunizacao criarImunizacao(Imunizacao imunizacao){
       return imunizacaoRepository.save(imunizacao);
    }

    // Alterar imunização
    public Imunizacao alteraImunizacao(Integer id, Imunizacao imunizacao){
        Optional<Imunizacao> resultado = imunizacaoRepository.findById(id);

        if(resultado.isPresent()){
           Imunizacao imunizacaoExistente = resultado.get();
           imunizacaoExistente.setDataAplicacao(imunizacao.getDataAplicacao());
           imunizacaoExistente.setFabricante(imunizacao.getFabricante());
           imunizacaoExistente.setLote(imunizacao.getLote());
           imunizacaoExistente.setLocalAplicacao(imunizacao.getLocalAplicacao());
           imunizacaoExistente.setProfissionalAplicador(imunizacao.getProfissionalAplicador());
           return imunizacaoRepository.save(imunizacaoExistente);
        }else{
            throw new RuntimeException("Imunização n encontrada para o id informado:" + id);
        }
    }

    // Excluir Imunização por Id
    public String excluirImunização(Integer id){
        Optional<Imunizacao> resultado = imunizacaoRepository.findById(id);

        if (resultado.isPresent()) {
            imunizacaoRepository.deleteById(id);
            return String.format("Id excluido com sucesso id: d%", id);
        }else{
            throw new RuntimeException("Id não encontrado no banco: id " + id);
        }
    }

    // Excluir TODAS a Imunização de um paciente
    public String excluirTodasImunizacaoPorPaciente(Integer idPaciente){
       List <Imunizacao> imunizacaos =  imunizacaoRepository.findByPacienteId(idPaciente);
        if(!imunizacaos.isEmpty()){
             imunizacaoRepository.deleteByPacienteId(idPaciente);
            return String.format("Todas as imnuzações do paciente %d foram excluidas", idPaciente);
        }else{
            throw new RuntimeException("O paciente do Id: " + idPaciente +" não tem nehuma Imunização ");
        }
    }

    // Buscar por Id
    public Imunizacao buscarPorId(Integer id){
       Optional<Imunizacao> resultado = imunizacaoRepository.findById(id);

       if(resultado.isPresent()){
        return resultado.get();
       }else{
        throw new RuntimeException("Imunização não encontrada para o id: " + id);
       }
    }

    // Buscr por Id do Paciente
    public List<Imunizacao> buscarPorPaciente(Integer idPaciente){
        return imunizacaoRepository.findByPacienteId(idPaciente);
    }

    // Consultar Imunizações por id do paciente e intervalo de aplicação
    public List<Imunizacao> buscarPorIdPacienteandIntervaloAplicacao(Integer idPaciente ,LocalDate dataInicio, LocalDate dataFim){
        return imunizacaoRepository.
            findByPacienteIdAndDataAplicacaoBetween(
                idPaciente, dataInicio, dataFim
            );
    }

    


}
