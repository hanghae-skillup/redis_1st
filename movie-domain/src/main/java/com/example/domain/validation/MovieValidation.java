package com.example.domain.validation;

import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class MovieValidation {

    private static final int MAX_TITLE_LENGTH = 50;

    public void validateTitleLength(String title) {
        if (title == null) {  // title 이 null 이면 검증 로직을 실행하지 않음
            return;
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new CustomException(ErrorCode.INVALID_TITLE_LENGTH);
        }
    }

}
