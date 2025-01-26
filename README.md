## 기술 스택
- Spring Boot 3.4.1
- Gradle
- Java 17
- Docker Compose
- MySQL
- JPA
- IntelliJ Http Request

<br/>

## How to run
1. MySQL docker container 생성 및 백그라운드 실행
    ```
    docker-compose up -d
    ```

2. adapter.web 모듈 내에 있는 `ApiApplication` 실행

<br/>

## Architecture
- `헥사고날 아키텍처`를 기반으로 `멀티 모듈`로 구성하였습니다.
- domain
- application
- adapter
   - web
   - persistence

### Module
1. domain
    - 외부 의존성 없이 독립적으로 동작하는 모듈입니다.
    - 애플리케이션의 핵심 비즈니스 로직과 엔티티를 포함합니다.
    - 특정 프레임워크나 규약에 종속되지 않도록 도메인은 순수 자바 객체(POJO) 로 구성되었습니다.
      
2. application
    - 비즈니스 로직의 흐름을 제어하는 모듈입니다.
    - `usecase`(port) 는 in-port에 해당하며, in-adapter 에서 호출할수 있도록 의존성 역전 원칙을 적용하여 인터페이스로 선언하였습니다.
    - `usecase` 의 구현은 `service` 클래스에서 이루어집니다.
    - `port` 는 out-port 에 해당하며, out adpater 을 사용할 수 있도록 의존성 역전 원칙을 적용하여 인터페이스로 선언하였습니다.
    - `port` 의 구현체는 `adapter` 모듈에서 작성되었습니다.
      
3. adapter.web (in-adapter)
    - 사용자와의 상호작용을 처리하는 입력 및 출력 모듈입니다.
    - 컨틀롤러를 중심으로 동작하며, 웹 진입접 역할을 합니다. 
    - 애플리케이션의 진입점인 `Application` 클래스가 위치합니다.
      
4. adapter.persistence (out-adpater)
   - 데이터베이스와의 상호 작용을 처리하며, 영속성을 관리하는 모듈입니다.
   - 리포지토리와 엔티티를 포함하고 있습니다.
     
### 의존성 방향
- adapter.web -> application -> domain
- adapter.persistence -> application -> domain

<br/>

## Entity Relationship Diagram
<img width="822" alt="redis_erd" src="https://github.com/user-attachments/assets/e29a1f00-8f2c-40bf-99df-749d741025e9" />


### Movie (영화) 테이블
- 영화의 기본 정보를 저장하는 테이블
- 컬럼 정보
    - rating : 영화의 관람 등급
        - 거의 변동성이 없으므로 enum 으로 관리해주었습니다.
    - running_time : 영화의 상영 시간 (분 단위)
    - genre : 영화 장르
        - 거의 변동성이 없으므로 eunm 으로 관리해주었습니다.
     
### Theater(극장) 테이블
- 극장의 정보를 저장하는 테이블

### ShowTime(상영 시간) 테이블
- 특정 영화의 상영 정보를 저장하는 테이블
- 컬럼 정보
  - movie_id : 상영 영화의 식별자
   - Movie 테이블의 id 를 참조
  - theater_id : 상영 극장의 식별자
   - Theater 테이블의 id 를 참조
  - started_at : 상영 시작 시간
  - ended_at : 상영 종료 시간
 
### 테이블 간 관계
- Movie <-> ShotTime
    - 1:N 관계로 한 영화는 여러 상영 정보를 가질 수 있습니다.
- Theater <-> ShowTime
    - 1:N 관계로 한 극장은 여러 상영 정보를 가질 수 있습니다.
 
## 성능 테스트 보고서
[성능 테스트 보고서 보러가기](https://www.notion.so/17fe5ab6924e80109e6efc1149612b21?pvs=4)
