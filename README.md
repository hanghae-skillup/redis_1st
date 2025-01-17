## [본 과정] 이커머스 핵심 프로세스 구현
[단기 스킬업 Redis 교육 과정](https://hh-skillup.oopy.io/) 을 통해 상품 조회 및 주문 과정을 구현하며 현업에서 발생하는 문제를 Redis의 핵심 기술을 통해 해결합니다.
> Indexing, Caching을 통한 성능 개선 / 단계별 락 구현을 통한 동시성 이슈 해결 (낙관적/비관적 락, 분산락 등)

# **hhCinema 프로젝트**

## **프로젝트 설명**
`hhCinema`는 영화 관람 및 예약 시스템을 구현한 프로젝트입니다.  
핵사고날 구조를 기반으로 멀티모듈 아키텍처로 설계되었습니다.  
이 프로젝트는 **Docker Compose**를 사용하여 데이터베이스와 애플리케이션을 실행하며,  
**REST API**를 통해 영화 데이터를 조회하고 관리할 수 있습니다.

---

## **프로젝트 구조**

### **1. 모듈 설명**
- **`adapter` (실행 위치)**:  
  애플리케이션의 진입점(Entry Point)으로, REST API 컨트롤러 및 실행 코드를 포함합니다.  
  **위치**: `hhCinema/adapter`

- **`application`**:  
  비즈니스 로직을 처리하는 서비스 계층. 도메인 모델과의 상호작용을 담당합니다.  
  **위치**: `hhCinema/application`

- **`domain`**:  
  핵심 도메인 모델과 엔티티를 정의하는 계층. 비즈니스 로직의 독립성을 보장합니다.  
  **위치**: `hhCinema/domain`

- **`infrastructure`**:  
  데이터베이스 접근(JPA Repository) 및 외부 시스템과의 통합 로직을 포함합니다.  
  **위치**: `hhCinema/infrastructure`

- **`common`**:  
  공통으로 사용하는 유틸리티, 공통 상수, 예외 처리 등 여러 모듈에서 재사용 가능한 코드를 포함합니다.  
  **위치**: `hhCinema/common`

---

### **2. 추가 폴더**
- **`sql-scripts`**:  
  Docker Compose 실행 시 자동으로 실행되는 DDL 및 DML 스크립트를 포함합니다.  
  - **DDL**: 테이블 생성 SQL  
  - **DML**: 샘플 데이터 삽입 SQL  
  **위치**: `hhCinema/sql-scripts`

- **`docker-compose.yml`**:  
  데이터베이스와 애플리케이션을 컨테이너로 실행하는 설정 파일입니다.  
  **위치**: `hhCinema/docker-compose.yml`

---

### **3. 디렉토리 구조**
```plaintext
hhCinema/
├── adapter/               # 애플리케이션 실행 모듈
│   ├── src/main/java/     # 컨트롤러와 실행 코드
│   ├── src/test/resources/
│   │   └── movie-api-test.http  # API 테스트 파일
├── application/           # 비즈니스 로직 모듈
├── domain/                # 도메인 엔티티 모듈
├── infrastructure/        # 데이터베이스 및 외부 통합 모듈
├── common/                # 공통 유틸리티 및 상수 모듈
├── sql-scripts/           # 초기 SQL 스크립트 (DDL, DML)
│   ├── init.sql           # 샘플 DDL/DML 코드
├── docker-compose.yml     # Docker Compose 설정 파일
├── build.gradle           # 루트 Gradle 설정 파일
└── settings.gradle        # 멀티모듈 Gradle 설정 파일