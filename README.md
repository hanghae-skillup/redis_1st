## [본 과정] 이커머스 핵심 프로세스 구현

## 프로젝트 소개
- [단기 스킬업 Redis 교육 과정](https://hh-skillup.oopy.io/) 을 통해 상품 조회 및 주문 과정을 구현하며 현업에서 발생하는 문제를 Redis의 핵심 기술을 통해 해결합니다.
> Indexing, Caching을 통한 성능 개선 / 단계별 락 구현을 통한 동시성 이슈 해결 (낙관적/비관적 락, 분산락 등)

### API
- GET /api/v1/movies (영화목록 조회)

*** 

### Architecture
모듈 구성은 다음과 같다.
- module-infrastructure
- module-application
- module-domain

#### module-infrastructure
- in, out port로 구성되어 있으며, in은 api 를 호출하는 경우이며, out은 persistence 로 데이터를 가져오는 port로 구성
  
#### module-application 
- 도메인의 흐름을 정의하는 계층

#### module-domain
- 순수 도메인이 응집해있는 도메인 모듈로 movie, theater 등 도메인 존재

![image](https://github.com/user-attachments/assets/18153de2-c011-4613-a1c1-5ba82ea796a9)

***

### ERD
- movie, movie_genre, movie_theater, theater, seat, screening_schedule 테이블로 구성되어 있으며 각 테이블 정의는 다음과 같다.

#### movie
- 영화 정보가 정의되어있는 테이블
 
#### movie_genre
- 영화 장르 정보가 정의되어있는 테이블

#### movie_theater
- 영화와 상영관간의 관계 정보가 정의되어있는 테이블

#### theater
- 상영관 정보가 정의되어있는 테이블

#### seat
- 상영관의 좌석 정보가 정의되어있는 테이블

#### screning_schedule
- 영화 상영표가 정의되어있는 테이블

![image](https://github.com/user-attachments/assets/6d40c181-db9d-4af8-bc10-aa21cb25de6a)
