package hellojpa.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    @Size(max = 50, message = "제목은 최대 50글자 입력 가능합니다.")
    private String title;
    private String genre;
}