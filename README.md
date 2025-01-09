## [본 과정] 이커머스 핵심 프로세스 구현

## Multi Module
총 3개 모듈로 구성되있습니다.

### 1. movie-api
> 영화 도메인을 담당하고 Presentation Layer를 담당하는 모듈입니다.
### 2. core
> 공통으로 사용하는 Entity, DTO를 담당하는 모듈입니다.
### 3. infrastructure
> DB 인터페이스를 담당하는 모듈입니다. 
> MySQL, Redis 연결을 담당하고 데이터 입출력 로직을 포함합니다. 
> DB가 변경되어도 api, core 모듈의 코드는 변경하지 않습니다.

## Architecture
> 다른 도메인 확장성을 고려한 설계입니다.
> 모든 api 모듈에서는 Entity -> DTO로 변환하여 리턴합니다.

![arc](static/arc.png)

## Table Design
![erd_db](static/erd.png)