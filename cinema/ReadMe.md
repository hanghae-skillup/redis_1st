# 프로젝트 README

## 프로젝트 소개
이 프로젝트는 영화 상영 정보와 관련된 기능을 제공하는 **멀티 모듈 프로젝트**입니다. 영화관, 영화, 상영 일정, 상영관 등의 정보를 효율적으로 관리하고 제공하는 시스템으로 설계되었습니다. 코드는 Kotlin과 Spring Boot를 기반으로 작성되었으며, 도메인 중심 설계를 적용하였습니다.

---

## 모듈 구조

### 1. **`cinema-domain`**
- **역할:**
    - 프로젝트의 핵심 비즈니스 로직과 엔티티가 정의된 모듈입니다.
    - 데이터베이스와의 상호작용을 위한 JPA 엔티티와 Repository를 포함합니다.
- **주요 패키지 구성:**
    - `cinema`: 영화관 정보 (Cinema)
    - `movie`: 영화 정보 (Movie, Genre, MovieRating 등)
    - `schedule`: 영화 상영 일정 (MovieSchedule)
    - `theater`: 상영관 정보 (Theater)
    - `infra.jpa`: JPA Repository 및 QueryDSL 설정

### 2. **`cinema-api`**
- **역할:**
    - 외부 사용자에게 API를 제공하는 모듈입니다.
    - Controller와 DTO 계층을 포함하여 클라이언트와의 인터페이스를 담당합니다.
- **주요 패키지 구성:**
    - `application`: 비즈니스 로직을 처리하는 Service 계층
    - `interface`: API 요청을 처리하는 Controller와 DTO 정의

### 3. **`cinema-admin`**
- **역할:**
    - 관리자용 기능(예: 상영 스케줄 관리)을 제공하는 모듈입니다.

---

## 아키텍처 설명

### 전체 아키텍처

- **멀티 모듈 설계:**
    - `cinema-domain`: 핵심 도메인 로직과 데이터 액세스를 처리합니다.
    - `cinema-api`: 사용자 요청을 처리하고 비즈니스 로직을 호출합니다.
    - `cinema-admin`: 관리자 기능을 제공합니다.

- **계층 구조:**
    - **Controller (API 모듈):** 사용자 요청을 받아 Service 계층으로 전달합니다.
    - **Service (Domain 모듈):** 비즈니스 로직을 처리하며, Repository와 상호작용합니다.
    - **Repository (Domain 모듈):** 데이터베이스와 직접적으로 통신합니다.

### QueryDSL 적용
- `cinema-domain` 모듈에 QueryDSL을 설정하여 동적 쿼리를 지원합니다.
- `MovieScheduleRepositoryCustom` 인터페이스와 `MovieScheduleRepositoryCustomImpl` 구현체를 통해 확장된 Repository 기능을 제공합니다.

---

## 데이터베이스 테이블 설계

### 주요 테이블

#### 1. `cinema`
- **영화관 정보를 저장**
- 주요 컬럼:
    - `id` (PK): 영화관 ID
    - `name`: 영화관 이름
    - `address`: 영화관 주소
    - `contact_number`: 연락처

#### 2. `movie`
- **영화 정보를 저장**
- 주요 컬럼:
    - `id` (PK): 영화 ID
    - `title`: 영화 제목
    - `genre`: 영화 장르
    - `movie_rating`: 영상물 등급
    - `release_date`: 개봉일
    - `running_time_minutes`: 러닝 타임
    - `thumbnail_url`: 포스터 URL

#### 3. `theater`
- **상영관 정보를 저장**
- 주요 컬럼:
    - `id` (PK): 상영관 ID
    - `title`: 상영관 이름
    - `seat_layout`: 좌석 배치
    - `cinema_id` (FK): 영화관 ID

#### 4. `movie_schedule`
- **영화 상영 일정을 저장**
- 주요 컬럼:
    - `id` (PK): 상영 일정 ID
    - `start_at`: 상영 시작 시간
    - `end_at`: 상영 종료 시간
    - `movie_id` (FK): 영화 ID
    - `theater_id` (FK): 상영관 ID
    - `cinema_id` (FK): 영화관 ID

---

## 실행 방법
``` bash
docker-compose -f compose.yaml up -d

```

## 테스트
http.movie.http

## 고민포인트

#### UI에서 제공해준 페이지네이션

UI는 영화와 상영관 단위로 리스트를 제공하며, 하위에 시간표를 표시해야 했습니다.
하지만 페이지네이션을 고려하면 (영화, 상영관) 의 수가 요청한 수를 제공할 수있는 구조여야 합니다.

최초 접근 방법은 고유한 영화와 상영관 쌍을 먼저 뽑은 후, 시간표를 추가 쿼리로 조회하려 했습니다.
그러나 전체 정렬을 movie의 개봉일 기준으로 해야 했기 때문에 DISTINCT를 사용할 수 없었습니다.

최종 대안:

상영관 정보를 시간표와 동일한 위계로 내려 그룹핑을 애플리케이션 레벨에서 처리했습니다.
이를 통해 영화 개봉일로 정렬하면서도 UI 요구사항을 만족시킬 수 있었습니다.