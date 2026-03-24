package br.com.carteiravacina.service;

import br.com.carteiravacina.model.Imunizacao;
import br.com.carteiravacina.repository.ImunizacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ImunizacaoService {

    private final ImunizacaoRepository imunizacaoRepository;

    public ImunizacaoService(ImunizacaoRepository imunizacaoRepository) {
        this.imunizacaoRepository = imunizacaoRepository;
    }

    public List<Imunizacao> listarTodos() {
        return imunizacaoRepository.findAll();
    }

    public Imunizacao criarImunizacao(Imunizacao imunizacao) {
        return imunizacaoRepository.save(imunizacao);
    }

    public Imunizacao alteraImunizacao(Long id, Imunizacao imunizacao) {
        Optional<Imunizacao> resultado = imunizacaoRepository.findById(id);

        if (resultado.isPresent()) {
            Imunizacao imunizacaoExistente = resultado.get();
            imunizacaoExistente.setDataAplicacao(imunizacao.getDataAplicacao());
            imunizacaoExistente.setFabricante(imunizacao.getFabricante());
            imunizacaoExistente.setLote(imunizacao.getLote());
            imunizacaoExistente.setLocalAplicacao(imunizacao.getLocalAplicacao());
            imunizacaoExistente.setProfissionalAplicador(imunizacao.getProfissionalAplicador());
            return imunizacaoRepository.save(imunizacaoExistente);
        } else {
            throw new RuntimeException("Imunização não encontrada para o id informado: " + id);
        }
    }

    public String excluirImunizacao(Long id) {
        Optional<Imunizacao> resultado = imunizacaoRepository.findById(id);

        if (resultado.isPresent()) {
            imunizacaoRepository.deleteById(id);
            return String.format("Id excluído com sucesso: %d", id);
        } else {
            throw new RuntimeException("Id não encontrado no banco: " + id);
        }
    }

    public String excluirTodasImunizacaoPorPaciente(Long idPaciente) {
        List<Imunizacao> imunizacoes = imunizacaoRepository.findByPaciente_IdPaciente(idPaciente);

        if (!imunizacoes.isEmpty()) {
            imunizacaoRepository.deleteByPaciente_IdPaciente(idPaciente);
            return String.format("Todas as imunizações do paciente %d foram excluídas", idPaciente);
        } else {
            throw new RuntimeException("O paciente de id " + idPaciente + " não tem nenhuma imunização");
        }
    }

    public Imunizacao buscarPorId(Long id) {
        Optional<Imunizacao> resultado = imunizacaoRepository.findById(id);

        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            throw new RuntimeException("Imunização não encontrada para o id: " + id);
        }
    }

    public List<Imunizacao> buscarPorPaciente(Long idPaciente) {
        return imunizacaoRepository.findByPaciente_IdPaciente(idPaciente);
    }

    public List<Imunizacao> buscarPorIdPacienteAndIntervaloAplicacao(
            Long idPaciente,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        return imunizacaoRepository.findByPaciente_IdPacienteAndDataAplicacaoBetween(
                idPaciente, dataInicio, dataFim
        );
    }
}