package project.redis.common.exception;


import lombok.Getter;

@Getter
public class DataInvalidException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Object[] args;

    public DataInvalidException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessageId());
        this.errorCode = errorCode;
        this.args = args;
    }
}
