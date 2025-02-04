package project.redis.presentation.cinema.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
