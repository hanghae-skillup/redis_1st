package org.example.common.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.common.response.ResponseStatus;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder({"code", "status", "message", "timestamp"})
public class BaseErrorResponse implements ResponseStatus {
    private final int code;
    private final int status;
    private final String message;

    public BaseErrorResponse(ResponseStatus status){
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
