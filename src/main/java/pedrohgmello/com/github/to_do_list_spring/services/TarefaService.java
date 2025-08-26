package pedrohgmello.com.github.to_do_list_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedrohgmello.com.github.to_do_list_spring.dto.TarefaRequestDTO;
import pedrohgmello.com.github.to_do_list_spring.exceptions.AcessoNaoAutorizadoException;
import pedrohgmello.com.github.to_do_list_spring.exceptions.RecursoNotFoundException;
import pedrohgmello.com.github.to_do_list_spring.model.Tarefa;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;
import pedrohgmello.com.github.to_do_list_spring.repository.TarefaRepository;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(String titulo, String descricao, LocalDateTime dataCriacao, Usuario usuario) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataCriacao(dataCriacao);
        tarefa.setConcluida(false);
        tarefa.setUsuario(usuario);

        return tarefaRepository.save(tarefa);
    }

    public Set<Tarefa> filtrarTarefasPorUsuario(Usuario usuario){
        return tarefaRepository.findByUsuario(usuario);
    }

    public Tarefa filtrarPorIdEValidar(Long id, Usuario usuario){
        Integer i = Long.valueOf(id).intValue();
        Tarefa tarefa = tarefaRepository.findById(i).
                orElseThrow(() -> new RecursoNotFoundException("Tarefa não encontrada"));
        if(!tarefa.getUsuario().getId().equals(usuario.getId())){
            throw new AcessoNaoAutorizadoException("Acesso negado");
        }
        return tarefa;
    }

    public Tarefa atualizarTarefa(Long id, TarefaRequestDTO newTarefa, Usuario usuario){
        Tarefa tarefa = filtrarPorIdEValidar(id, usuario);
        System.out.println("DTO RECEBIDO: " + newTarefa);
        if(newTarefa.titulo() != null)
            tarefa.setTitulo(newTarefa.titulo());
        if(newTarefa.descricao() != null)
            tarefa.setDescricao(newTarefa.descricao());
        tarefaRepository.save(tarefa);
        return tarefa;
    }

    public void deletarTarefa(Long id, Usuario usuario){
        Tarefa tarefa = filtrarPorIdEValidar(id, usuario);
        tarefaRepository.delete(tarefa);
    }

    public Tarefa concluirTarefa(Long id, Usuario usuario){
        Tarefa tarefa = filtrarPorIdEValidar(id, usuario);
        tarefa.setConcluida(true);
        tarefaRepository.save(tarefa);
        return tarefa;
    }
}
