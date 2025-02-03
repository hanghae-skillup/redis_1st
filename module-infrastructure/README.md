## [Infrastructure Module]

### 책임

1. **외부 통신 및 기술 로직 담당**
    - 데이터베이스, 외부 API, 메시지 큐 등 외부 시스템과의 통신을 처리.
    - JPA, Flyway 등 구체적인 기술을 활용하여 데이터를 저장하고 조회.

2. **Application 계층의 외부 의존성 제거**
    - Domain 객체를 활용하여 외부 기술의 의존성을 철저히 제거.
    - Application 계층은 Infrastructure 계층의 존재를 알지 못하며, Port-Adapter 패턴을 통해 간접적으로 의존.

3. **데이터 변환 및 매핑**
    - JPA Entity를 Domain 객체로 변환하여 Application 계층에 전달.
    - Domain 객체에서 필요한 정보만 추출하여 비즈니스 로직에 활용 가능.

---

### 구조

- **Port-Adapter 패턴 적용**:
    - Application 계층에서 정의한 `ScreeningQueryPort` 인터페이스를 구현.
    - 실제 기술 스택(JPA)을 사용하여 데이터를 처리.

- **Domain과의 연결**:
    - Infrastructure 계층에서 Domain 객체를 참조하여 데이터를 전달.
    - 데이터베이스 Entity(`ScreeningJpaEntity`)를 Domain 객체(`Screening`)로 매핑.

---

### 주요 클래스

#### **`ScreeningQueryAdapter`**
- Application 계층에서 정의한 `ScreeningQueryPort` 인터페이스를 구현.
- JPA를 사용하여 데이터를 조회하고, Domain 객체로 변환하여 반환.

```java
@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class ScreeningQueryAdapter implements ScreeningQueryPort {

    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public List<Screening> getScreenings(int maxScreeningDay) {

        LocalDate maxScreeningDate = LocalDate.now().plusDays(maxScreeningDay);

        List<ScreeningJpaEntity> screenings
                = screeningJpaRepository.findAllOrderByReleaseDescAndScreenStartTimeAsc(maxScreeningDate);

        return screenings.stream()
                .map(ScreeningInfraMapper::toScreening)
                .toList();
    }
}
```

---

#### **`ScreeningInfraMapper`**
- Entity와 Domain 객체 간의 변환을 처리.

```java
public class ScreeningInfraMapper {

    public static Screening toScreening(ScreeningJpaEntity entity) {
        return Screening.generateScreening(
                entity.getScreeningId(),
                entity.getScreeningStartTime(),
                entity.getScreeningEndTime(),
                MovieInfraMapper.toMovie(entity.getMovie()),
                CinemaInfraMapper.toCinema(entity.getCinema())
        );
    }

}
```

---

### 설계 원칙

1. **Port-Adapter 패턴 준수**:
    - Port(`ScreeningQueryPort`)를 Application 계층에서 정의하고, Adapter(`ScreeningQueryAdapter`)를 통해 구현.

2. **기술과 비즈니스의 분리**:
    - JPA, Flyway와 같은 기술적인 구현은 Infrastructure 계층에서만 관리.
    - Application 계층은 오직 Domain 객체만 활용.

3. **확장성**:
    - 데이터베이스가 변경되거나 다른 기술 스택(NoSQL, 외부 API 등)이 추가되더라도 Port-Adapter 패턴을 통해 쉽게 확장 가능.

4. **Domain 중심 설계**:
    - 데이터를 Domain 객체로 변환하여 Application 계층에 전달.
    - 비즈니스 로직은 Application 계층에서 수행.

---

### 장점
- **유지보수성**: 외부 기술 변경(JPA -> 다른 ORM) 시에도 Application 계층은 수정이 필요 없음.
- **확장성**: 새로운 데이터 소스나 외부 API 추가 시 Port와 Adapter만 추가하면 됨.
- **독립성**: 비즈니스 로직과 기술 로직을 철저히 분리하여 각 계층의 역할을 명확히 함.

---
