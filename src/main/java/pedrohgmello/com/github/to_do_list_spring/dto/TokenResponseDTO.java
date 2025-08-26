package pedrohgmello.com.github.to_do_list_spring.dto;

public record TokenResponseDTO(
        String token
) {
    @Override
    public String token() {
        return token;
    }
}
