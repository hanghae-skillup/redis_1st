package com.hanghae.application.dto.request;

import com.hanghae.domain.model.enums.MovieGenre;

import java.nio.charset.StandardCharsets;

public record MovieScheduleRequestDto (
        /*  jakarta.validation 을 사용하여 간단하게 아래처럼 변수명위에 어노테이션을 추가하여 체크 가능
            예)
            @Size(max = 30, message = "이름은 최대 30자까지만 입력할 수 있습니다.")
            String title

            해당 방법을 쓸 경우 컨트롤러 인자값에 @Valid를 붙여 줘야 한다.
            예) public List<RequestDto> getMovie(@Valid @ModelAttribute RequestDto requestDto) { ... }
         */
        String title,
        MovieGenre genre
){
    /* record의 validation 체크는 기본적으로 생성자 방식으로 사용 가능 */
    public MovieScheduleRequestDto {
        if (title != null) {
            int titleByteSize = title.getBytes(StandardCharsets.UTF_8).length;
            if(titleByteSize > 30) {//30 바이트보다 클 경우 예외 처리
                throw new IllegalArgumentException("영화제목의 크기가 너무 큽니다.");
            }
        }
    }

}
