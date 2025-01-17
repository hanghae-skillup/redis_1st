## [Presentation Module]

### 책임

1. **Client의 요청 처리**
    - 클라이언트로부터 들어오는 요청을 처리합니다.
    - 요청 데이터(`ScreeningsQueryRequest`)를 바탕으로 유효성을 검증하고 필요한 데이터를 추출합니다.

2. **Application 모듈에 로직 위임**
    - 요청 데이터를 기반으로 Application 모듈의 인터페이스(`ScreeningQueryUseCase`)를 호출합니다.
    - 비즈니스 로직은 Application 모듈에서 처리하며, Presentation 모듈은 단순히 요청/응답에만 집중합니다.

3. **적절한 응답 반환**
    - Application 모듈에서 반환된 결과를 바탕으로 클라이언트가 원하는 응답 형식으로 변환합니다.
    - 예: 영화 데이터를 `GroupedScreeningResponse`로 그룹화하여 반환합니다.

### 구조

#### **Presentation과 Application 모듈의 분리**
- Presentation 모듈은 Application 모듈과 직접적으로 의존하지 않고, **Port-Adapter 패턴**을 통해 통신합니다.
    - **Port**: `ScreeningQueryUseCase` 인터페이스로, Application 모듈에서 구현됩니다.
    - **Adapter**: Application 모듈의 실제 구현체가 주입됩니다.

#### **파라미터 설계**
- 클라이언트로부터 받은 요청(`ScreeningsQueryRequest`)은 Presentation 모듈 내에서 처리됩니다.
- Application 모듈과의 통신에는 별도의 파라미터 객체(`ScreeningsQueryParam`)를 사용하여 **Presentation의 변경이 Application에 영향을 미치지 않도록 설계**했습니다.

### 주요 클래스

#### **`ScreeningController`**
- 클라이언트 요청을 처리하고 응답을 반환하는 API 컨트롤러.

```java
@RestController
@RequestMapping("/api/v1/screenings")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningQueryUseCase screeningQueryUseCase;

    @GetMapping
    public ResponseEntity<List<GroupedScreeningResponse>> getScreenings(ScreeningsQueryRequest request) {

        List<Screening> screenings = screeningQueryUseCase.getScreenings(
                ScreeningsQueryParam.builder()
                        .maxScreeningDay(request.getMaxScreeningDay())
                        .build()
        );

        return ResponseEntity.ok(ScreeningAppMapper.toGroupedScreeningResponse(screenings));
    }
}
```

#### **`ScreeningsQueryRequest`**
- 클라이언트로부터 전달된 요청 데이터를 캡슐화한 클래스.

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningsQueryRequest {
    @Builder.Default
    private int maxScreeningDay = 2;
}
```

#### **`GroupedScreeningResponse`**
- 영화별로 그룹화된 응답 데이터를 제공하는 DTO.

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupedScreeningResponse {
    private String movieId;
    private String movieTitle;
    private String rating;
    private LocalDate releaseDate;
    private String thumbnailUrl;
    private int runningMinTime;
    private String genreId;
    private String genreName;
    private List<ScreeningDetail> screenings;
    
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScreeningDetail {
        private String screeningId;
        private LocalDateTime screeningStartTime;
        private LocalDateTime screeningEndTime;
        private String cinemaId;
        private String cinemaName;
    }
}
```