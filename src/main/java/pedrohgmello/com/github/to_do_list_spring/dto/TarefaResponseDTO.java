package pedrohgmello.com.github.to_do_list_spring.dto;

import java.time.LocalDateTime;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        boolean concluida,
        LocalDateTime dataDeCriacao,
        Long idUsuario
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public String descricao() {
        return descricao;
    }

    @Override
    public boolean concluida() {
        return concluida;
    }

    @Override
    public LocalDateTime dataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public Long idUsuario() {
        return idUsuario;
    }
}
