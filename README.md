## [본 과정] 이커머스 핵심 프로세스 구현
[단기 스킬업 Redis 교육 과정](https://hh-skillup.oopy.io/) 을 통해 상품 조회 및 주문 과정을 구현하며 현업에서 발생하는 문제를 Redis의 핵심 기술을 통해 해결합니다.
> Indexing, Caching을 통한 성능 개선 / 단계별 락 구현을 통한 동시성 이슈 해결 (낙관적/비관적 락, 분산락 등)


Table Design

테이블:
상영(Screenings), 
영화(Movies), 
사용자(Users),
극장(Theaters),
좌석예약(SeatReservation)

이후 방향에 따라서 사용자가 영화를 예매한 예매 정보를 추가할 예정이다.

 우선 제 1차 비지니스 요구사항이 영화관의 메인페이지를 보여주는 기능이다.
필요한 내용: 영화제목, 영상물 등급, 개봉일, 썸네일 이미지, 러닝 타임(분), 영화 장르, 상영관 이름, 상영시간표

1.  User가 가입을 했다는 가정하에 진행되는 비지니스이기 때문에 User 테이블을 설정했다.
컬럼은 user_id, user_name, user_age등 간단한 정보만 기입했다.

2.  Movie 영화제목, 영상물등급, 개봉일, 썸네일 이미지, 러닝 타임, 영화 장르등 영화에 관련된 정보가 필요하기 때문에 Movie 테이블을 설정했다.
컬럼은  movie_id, title,  rating,   release_date,  thumbnail_url ,  running_time.  genre 로 설정했다.

3. Movie가 상영하는 극장과 상영에 대한 정보를 나누기 위해서 Screenings 테이블과 Theater 테이블을 설정했다. 상영과 상영관을 분리한 이유는
상영에 상영관들이 수정될 경우 수정이상이나 삭제이상등 중복된 데이터가 생길 것 같아서 상영과 상영관을 분리했다.

4. 극장(Theaters)은
  theater_id, theater_name 만 가지는 간단한 정보를 가졌고,
상영은 
 screening_id, movie_id ,theater_id ,start_time , end_time ,screening_date  컬럼을 두었다.

5. common 부분에는 created_at, created_by, update_by, updated_at을 공통 부분으로 나누어서 모든 테이블에 입력했다.

하나에 극장에는 여러가지 상영이 들어갈 수 있고, 하나의 상영은 하나의 극장만 가지기 때문에 극장 1: 상영 N 으로 설정했다.
또한 영화 한편은 여러가지 상영을 가지고, 상영은 하나의 영화만 가지기에 영화 1: 상영 N 으로 설정했다.
하지만 DB 설계만 했고, 외래키는 따로 제약을 걸진 않았다.

좌석예약은 극장의 좌석 예약을 위해서
  seat_reservation_id , Screenings_id, seat_number, is_reserved 
설정했고 추후에 좌석예약 비지니스에 사용할 예정이다.


/var/folders/_y/spb8kyz900d3ffjfvdr4tlnh0000gn/T/TemporaryItems/NSIRD_screencaptureui_DGDBLX/스크린샷 2025-01-10 22.05.19.png

/Users/jangtaehwan/Downloads/Untitled.pdf


Multi Module, Architecture

둘이 동일한 관점으로 보자면, 우선 모듈은 Layered + clean Architecture로 구현했다.

최 상위 계층부터 presentation(controller) -> application(facade) -> domain(entity,service, repository(interface) ) -> infrastructure(repository, jpa, mybatis)
아래와 같이 모듈로 나누었다.

모듈로 나누면서 느낀점은
혼자 간단한 프로젝트를 하는데 너무 많은 설정이 필요하다?? 였다.

하나의 단일 모듈로 Layered + clean Architecture를 구현했을 때와 비교했을 때 장점은 하나가 있었다. 모듈끼리의 의존성을 명시하지 않으면 의존자체를 할 수 없어서. 실수로 하는 역참조를 방지할 수 있다는 것이다. 
아직 멀티 모듈이 처음이라 차차 적용해 나가겠지만, 가장 장점이라고 느낀 것은 이거였고, 설정이 너무 많아서 이것은 초기 비용이 높다는 단점이 느껴졌다.


아키텍쳐는 기본적인 Layered Architecture의 구조는 presentation -> application -> domain -> infra인데 domain 계층에 repository interface를 구현해 놓아서
presentation -> application -> domain(repository) <- infra(repositoryImpl)의 구조를 만들어서 domain계층을 완전히 분리시키는 방식을 선택 했다
