package project.redis.common.exception;

import lombok.Getter;

@Getter
public class ErrorCode {

    private final String messageId;

    public ErrorCode(String messageId) {
        this.messageId = messageId;
    }

    public static ErrorCode NOT_FOUND = new ErrorCode("COMMON.ERROR.NOT_FOUND");
    public static ErrorCode NOT_NULL = new ErrorCode("COMMON.ERROR.NOT_NULL");
    public static ErrorCode NOT_BLANK = new ErrorCode("COMMON.ERROR.NOT_BLANK");

    /* SEAT */
    public static ErrorCode SEAT_REQUIRED_SERIES = new ErrorCode(
            "SEAT.ERROR.REQUIRED_SERIES"
    );
    public static final ErrorCode SEAT_DUPLICATED = new ErrorCode(
            "SEAT.ERROR.DUPLICATED"
    );
    public static final ErrorCode SEAT_EXCEED_COUNT = new ErrorCode(
            "SEAT.ERROR.EXCEED_COUNT"
    );
    public static final ErrorCode SEAT_ALREADY_RESERVED = new ErrorCode(
            "SEAT.ERROR.ALREADY_RESERVED"
    );

    /* SCREENING */
    public static ErrorCode SCREENING_REQUIRED_LATER_NOW = new ErrorCode(
            "SCREENING.ERROR.REQUIRED_LATER_NOW"
    );


}
