package hellojpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateLimitResponseDto<T> {

    private int status;
    private String code;
    private String message;
    private T data;

    public static <T> RateLimitResponseDto<T> success(T data){
        return new RateLimitResponseDto<>(200, "success", "요청에 성공했습니다.", data);
    }

    public static <T> RateLimitResponseDto<T> error(){
        return new RateLimitResponseDto<>(429, "RATE_LIMIT_EXCEEDED", "요청 제한 횟수를 초과했습니다.", null);
    }
}