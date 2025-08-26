package pedrohgmello.com.github.to_do_list_spring.dto;

import pedrohgmello.com.github.to_do_list_spring.model.Usuario;

public class UsuarioResponseDTO {
    private String login;
    private Long id;
    public UsuarioResponseDTO(Usuario usuario) {
        this.login = usuario.getUsername();
        this.id = usuario.getId();
    }

    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }
}
