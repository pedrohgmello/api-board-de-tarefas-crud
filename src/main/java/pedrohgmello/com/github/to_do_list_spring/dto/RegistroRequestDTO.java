package pedrohgmello.com.github.to_do_list_spring.dto;

public record RegistroRequestDTO(
        String login,
        String password
) {
    @Override
    public String login() {
        return login;
    }

    @Override
    public String password() {
        return password;
    }
}
