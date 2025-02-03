## [Domain Module]

### 책임

1. **상호작용하는 객체 관리**
    - 영화(Screening), 영화관(Cinema), 영화 장르(Genre) 등 도메인 간의 상호작용을 관리.
    - 각 도메인은 데이터베이스 Entity와 분리된 **비즈니스 중심 객체**로 구성됩니다.

2. **도메인 로직 담당**
    - 각 도메인 클래스는 비즈니스 로직을 내포하며, 객체 간의 유효성 검사 및 데이터 조작을 처리합니다.
    - 예: 상영 시간이 유효한지 확인하거나 영화와 상영관 간의 연관성을 검증.

3. **도메인 간 로직 처리 (추후 도메인 서비스 도입 예정)**
    - 특정 도메인에 국한되지 않는 로직은 별도의 Domain Service로 분리하여 관리할 예정.
    - 예: 영화 상영 시간표 생성 로직.

---

### 구조

- **변경 불가능한 객체**:
    - 모든 도메인 객체는 불변성을 유지하여 안정성과 일관성을 확보합니다.
    - 예: `@Value`, 생성자 기반 객체 생성.

- **Entity와의 분리**:
    - 도메인 객체는 데이터베이스와의 의존성을 가지지 않으며, 기술적인 구현 세부사항을 포함하지 않습니다.
    - Entity 클래스는 Infrastructure 계층에 존재하며, 도메인 클래스와 별도로 관리됩니다.

---

### 주요 클래스

#### **`Screening`**
- 영화 상영 정보를 나타내는 도메인 클래스.
- 영화(`Movie`), 상영관(`Cinema`)과 연관.

```java
@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Screening {
    UUID screeningId;
    LocalDateTime screenStartTime;
    LocalDateTime screenEndTime;
    Movie movie;
    Cinema cinema;

    public static Screening generateScreening(
            UUID screeningId,
            LocalDateTime screenStartTime, LocalDateTime screenEndTime,
            Movie movie, Cinema cinema) {
        return new Screening(screeningId, screenStartTime, screenEndTime, movie, cinema);
    }
}
```

---

#### **`Movie`**
- 영화 정보를 담는 도메인 클래스.

```java
@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Movie {
    UUID movieId;
    String title;
    RatingClassification rating;
    LocalDate releaseDate;
    Genre genre;

    public static Movie generateMovie(
            UUID movieId, String title,
            RatingClassification rating, LocalDate releaseDate,
            Genre genre) {
        return new Movie(movieId, title, rating, releaseDate, genre);
    }
}
```

---

#### **설계 원칙**
1. **불변 객체**:
    - 모든 필드는 `final`로 선언하여 객체의 상태 변경을 방지.
    - 객체 생성은 정적 팩토리 메서드(`generateScreening`, `generateMovie`)를 통해 관리.

2. **Entity와 도메인의 분리**:
    - 데이터베이스와 상호작용하는 JPA Entity는 Infrastructure 계층에 존재.
    - 도메인은 순수 비즈니스 로직만 포함하여 데이터베이스 구현 변경 시 영향을 최소화.

3. **확장성**:
    - 도메인 클래스에 새로운 필드나 로직을 추가할 때, 기존 로직의 영향을 최소화.
    - Domain Service를 통해 도메인 간 복잡한 로직 분리 가능.

---
