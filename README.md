# hanghae-cinema
항해99 레디스

> spring boot 3.4.1  
> java 21  
> Mysql  

### 기록
> **스프링부트 실행시 `AdapterApplication.java` 파일로 실행**  
  
> `application.properties`파일은 `adapter`모듈에서 설정(DB포함 전부)
    * 스프링부트를 실행시키는 `SpringApplication`가 위치한곳  
  
> `domain model`에 `setter`사용할 예정으로 빌더패턴 사용 안함.
  
> QueryDSL 사용시 Q클래스를 사용하기 위해선 `build.gradle.kts`에 `dependencies` 추가 후 `해당 모듈` build 폴더 안에 Q클래스가 생성되어 있어야 사용 가능하다.   
>> 예) `cinema-infrastructure/build/generated/sources/annotationProcessor/java/main/com/hanghae/infrastructure/entity/QBaseEntity.java`  
>   
> 클린 후 빌드를 다시 진행시 build에 Q클래스 생성을 확인 필요  
  
> `QueryDSL`을 사용할 때 `infrastructure` 계층에서 도메인모델로 반환하게 되면 데이터 매핑 작업이 발생해 복잡해 진다.
> 그렇다고 `infrastructure` 계층에 dto를 반환하기엔 일관성이 깨진다. (dto변환은 `application` 계층에서 진행)
> 그래서 `infrastructure` 계층에서 `projection` 반환 후 `application` 계층에서 dto로 변환하려고함 (근데 의존성 떄문에 `projection`를 application에 위치시킴)
  
    
### 도커컴포즈  
* 도커컴포즈 설정파일
    * `docker-compose.yml`  
      * 탭: ❌, 스페이스바 2번: ⭕ 
    
* 도커컴포즈 변수 파일
    * `.env`
      * 보안을 위해 `.gitignore`에 추가해야 하지만 교육과제므로 github에 올림 
      * `.env.dev`, `.env.prod` 나누어 환경별로 다르게 적용도 가능

* 도커컴포즈 명령어
    * 실행 : $docker-compose up -d
    * 종료 : $docker-compose down
    * 실행 상태 확인 : $docker-compose ps
    * 볼륨 재거 : $docker-compose down --volumes
    * 로그 확인 : $docker-compose logs -f // `-f`옵션을 주면 실시간
    * 특정 서비스 시작 : $docker-compose start <서비스 이름>
    * 특정 서비스 종료 : $docker-compose stop <서비스 이름>
  
    
--------------------------------------------------------------
### 적용 아키텍처
> 헥사고날 아키텍처

### 모듈 구성
> cinema-adapter  
> cinema-application  
> cinema-domain  
> cinema-infrastructure

모듈은 헥사고날 아키텍처의 계층에 따라 나누었습니다.

>1. cinema-adapter  
>     * 외부로부터의 요청을 받는 역할을 합니다.
>     * 요청을 받아  `cinema-application`에 입력 포트를 호출 합니다.
>
>2. cinema-application  
>     * 입력/출력 포트에 대한 인터페이스를 정의하고 입력 포트에 비즈니스 로직을 구현 합니다.
>     * 입력 포트는 Adapter에서 호출됩니다.
>     * 출력 포트는 인터페이스만 정의하여 외부 시스템(DB등)과의 의존성을 최소화 합니다.
>     * domain service 로직은 application 모듈 port(in)에서 호출합니다. 
>
>3. cinema-domain  
>     * 외부에 의존하지 않는 독립적이고 핵심적인 비즈니스 모델 및 서비스 로직이 위치합니다.
>     * 외부 기술에도 의존하지 않기에 순수 자바로만 작성합니다.
>     * spring프레임워크등 프레임워크에도 의존하지 않습니다.
>     * 그렇기 떄문에 트랜잭션 관리는 애플리케이션 계층에서 처리합니다.
>
>4. cinema-infrastructure  
>    * JPA 엔티티와 데이터베이스에 접근하는 계층입니다.
>    * DB외에도 API등 외부 시스템과 상호작용 합니다.
  
도메인과 jpa엔티티를 나누었고 mapper클래스로 변환하도록 하였습니다.   
  
### 테이블 디자인

아래와 같이 7개 테이블로 구성하였습니다.
> `영화`  
> `상영관`  
> `상영시간표`  
> `영화예매내역`  
> `상영관좌석`  
> `회원`  
> `업로드파일`  

* ERD
    * `docs/cinema_erd.png`
* 테이블 생성 쿼리
    * `docs/cineam_create.sql`
* 초기 데이터
    * `docs/init_insert.sql`
* HTTP 테스트
    * `docs/cinema_test.http` 

`영화`테이블의 `영상물등급`, `영화장르` 컬럼에 적재되는 하는 코드값은 `java`에 `enum`을 사용해 처리  
