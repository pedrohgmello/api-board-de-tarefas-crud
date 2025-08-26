package pedrohgmello.com.github.to_do_list_spring.exceptions;

import java.time.Instant;

public record ApiErrorResponse(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
    @Override
    public Instant timestamp() {
        return timestamp;
    }

    @Override
    public Integer status() {
        return status;
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String path() {
        return path;
    }
}
