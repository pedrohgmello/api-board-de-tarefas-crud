package pedrohgmello.com.github.to_do_list_spring.dto;

import jakarta.validation.constraints.NotBlank;

public record TarefaRequestDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao
) {
}
