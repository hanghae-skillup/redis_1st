## [Application Module]

### 책임

1. **도메인 객체를 통해 Application 흐름 제어**
    - 도메인 간 상호작용을 조율하며 비즈니스 로직의 흐름을 제어.
    - 각 도메인 서비스와 협력하여 비즈니스 요구사항을 처리.

2. **Infrastructure 모듈과의 분리**
    - 데이터베이스와의 직접적인 통신은 Infrastructure 모듈에 위임.
    - `ScreeningQueryPort`와 같은 인터페이스를 통해 Application 계층은 외부 기술에 독립적.

3. **외부 기술과 무관한 설계**
    - Infrastructure에 종속되지 않으며, 비즈니스 로직의 흐름에만 집중.
    - 외부 기술 변경(JPA -> NoSQL 등)이 Application 계층에 영향을 미치지 않도록 설계.

---

### 구조

- **Port-Adapter 패턴 적용**:
    - Application 계층은 Port(인터페이스)를 정의하며, 실제 구현은 Infrastructure 모듈에서 Adapter로 처리.
    - 예: `ScreeningQueryPort`를 통해 데이터 소스를 추상화.

- **도메인 간 상호작용**:
    - 비즈니스 로직은 도메인 객체와 도메인 서비스를 활용하여 처리.
    - 데이터 변환 및 조율을 담당.

---

### 주요 클래스

#### **`ScreeningQueryService`**
- 비즈니스 로직의 흐름을 제어하는 서비스 클래스.
- Port 인터페이스(`ScreeningQueryPort`)를 통해 Infrastructure 모듈과 통신.

```java
@Service
@RequiredArgsConstructor
public class ScreeningQueryService implements ScreeningQueryUseCase {

    private final ScreeningQueryPort screeningQueryPort;

    @Override
    public List<Screening> getScreenings(ScreeningsQueryParam param) {
        return screeningQueryPort.getScreenings(param.getMaxScreeningDay());
    }
}
```

---

#### **`ScreeningQueryUseCase`**
- Application 계층의 핵심 인터페이스.
- Presentation 계층이 이를 통해 비즈니스 로직에 접근.

```java
public interface ScreeningQueryUseCase {

    List<Screening> getScreenings(ScreeningsQueryParam param);

}
```

---

#### **`ScreeningsQueryParam`**
- 비즈니스 로직 수행에 필요한 파라미터를 캡슐화한 클래스.

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningsQueryParam {
    private int maxScreeningDay;
}
```

---

### 설계 원칙

1. **Port-Adapter 패턴 준수**:
    - Port(인터페이스): `ScreeningQueryPort`.
    - Adapter(구현체): Infrastructure 모듈에서 구현.

2. **도메인 중심 설계**:
    - 비즈니스 로직은 도메인 객체 및 도메인 서비스와 협력하여 처리.
    - Application 계층은 비즈니스 흐름에 집중.

3. **기술 독립성**:
    - Application 계층은 외부 기술(JPA, DB 등)에 종속되지 않으며, 순수 Java 로직으로 구성.

4. **확장성**:
    - 새로운 데이터 소스나 비즈니스 요구사항이 추가되더라도 Port와 Adapter를 통해 확장 가능.

---

### 장점
- **유지보수성**: 외부 기술 변경 시 Application 계층에는 영향을 미치지 않음.
- **테스트 용이성**: Port를 Mocking하여 테스트 가능.
- **독립성**: 기술과 무관한 순수 비즈니스 로직 유지.

---
