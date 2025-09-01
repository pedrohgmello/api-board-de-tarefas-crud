package pedrohgmello.com.github.to_do_list_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pedrohgmello.com.github.to_do_list_spring.dto.TarefaRequestDTO;
import pedrohgmello.com.github.to_do_list_spring.dto.TarefaResponseDTO;
import pedrohgmello.com.github.to_do_list_spring.exceptions.AcessoNaoAutorizadoException;
import pedrohgmello.com.github.to_do_list_spring.exceptions.RegraDeNegocioException;
import pedrohgmello.com.github.to_do_list_spring.model.Tarefa;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;
import pedrohgmello.com.github.to_do_list_spring.services.TarefaService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> create(@RequestBody TarefaRequestDTO tarefa, @AuthenticationPrincipal Usuario usuarioLogado) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa.titulo(), tarefa.descricao(), LocalDateTime.now(), usuarioLogado);
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(novaTarefa.getId(), novaTarefa.getTitulo(), novaTarefa.getDescricao(), novaTarefa.isConcluida(), novaTarefa.getDataCriacao(), usuarioLogado.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaTarefa.getId())
                .toUri();
        return ResponseEntity.created(uri).body(tarefaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Set<TarefaResponseDTO>> read(@AuthenticationPrincipal Usuario usuarioLogado) {
        Set<Tarefa> tarefasPorUsuario = tarefaService.filtrarTarefasPorUsuario(usuarioLogado);
        Set<TarefaResponseDTO> tarefasPorUsuarioResponse = new HashSet<>();
        for(Tarefa tarefa : tarefasPorUsuario){
            tarefasPorUsuarioResponse.add(new  TarefaResponseDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.isConcluida(), tarefa.getDataCriacao(), usuarioLogado.getId()));
        }
        return ResponseEntity.ok(tarefasPorUsuarioResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> update(@RequestBody TarefaRequestDTO updatedTarefa, @AuthenticationPrincipal Usuario usuarioLogado, @PathVariable Long id) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, updatedTarefa, usuarioLogado);
        TarefaResponseDTO tarefaResponse = new TarefaResponseDTO(id, tarefaAtualizada.getTitulo(), tarefaAtualizada.getDescricao(), tarefaAtualizada.isConcluida(), tarefaAtualizada.getDataCriacao(), usuarioLogado.getId());
        return ResponseEntity.ok(tarefaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado){
        tarefaService.deletarTarefa(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponseDTO> concluirTarefa(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado){
        Tarefa tarefaConcluida = tarefaService.concluirTarefa(id, usuarioLogado);
        TarefaResponseDTO tarefaResponse = new TarefaResponseDTO(id, tarefaConcluida.getTitulo(), tarefaConcluida.getDescricao(), tarefaConcluida.isConcluida(), tarefaConcluida.getDataCriacao(), usuarioLogado.getId());
        return ResponseEntity.ok(tarefaResponse);
    }
}
