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


2주차 인덱스 도입.

1주차에 받은 피드백으로 고친 부분은

자바 플러그인과 공통 의존성을 root의 build.gradle에 모았고,
Main Method를 application 단으로 옮겼다.

그러고 DB에 대한 정보를 모두 infra 계층으로 옮겼다.
이것이 1주차에 대한 리펙토링이였다.

index와 redis에 대한 k6 테스트가 목적이였고,

동적으로 이루어지는 쿼리이기 때문에 querydsl을 사용하였고, domain과 Infra 계층에 querydsl관련 의존성을 추가해주었다.

1.기본 Test
기본적으로 Index를 넣지 않고 테스트를 진행했다.
스모크 테스트. 
최소유저로 짧은 테스트를 진행했다.
       /\      Grafana   /‾‾/  
    /\  /  \     |\  __   /  /   
   /  \/    \    | |/ /  /   ‾‾\ 
  /          \   |   (  |  (‾)  |
 / __________ \  |_|\_\  \_____/ 

     execution: local
        script: screening-test.js
        output: -

     scenarios: (100.00%) 1 scenario, 1 max VUs, 1m0s max duration (incl. graceful stop):
              * default: 1 looping VUs for 30s (gracefulStop: 30s)


     ✓ status is 200
     ✓ response time < 500ms

     checks.........................: 100.00% 60 out of 60
     data_received..................: 794 kB  26 kB/s
     data_sent......................: 3.9 kB  127 B/s
     http_req_blocked...............: avg=48.46µs min=6µs    med=13µs    max=1.05ms  p(90)=22.6µs  p(95)=29.1µs  
     http_req_connecting............: avg=5.43µs  min=0s     med=0s      max=163µs   p(90)=0s      p(95)=0s      
     http_req_duration..............: avg=19.91ms min=9.23ms med=20.16ms max=27.65ms p(90)=24.36ms p(95)=26.91ms 
       { expected_response:true }...: avg=19.91ms min=9.23ms med=20.16ms max=27.65ms p(90)=24.36ms p(95)=26.91ms 
     http_req_failed................: 0.00%   0 out of 30
     http_req_receiving.............: avg=1.06ms  min=458µs  med=1.05ms  max=1.69ms  p(90)=1.37ms  p(95)=1.53ms  
     http_req_sending...............: avg=61.03µs min=14µs   med=43µs    max=311µs   p(90)=137.4µs p(95)=156.39µs
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s      max=0s      p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=18.78ms min=8.76ms med=19.09ms max=25.91ms p(90)=23.18ms p(95)=25.51ms 
     http_reqs......................: 30      0.978945/s
     iteration_duration.............: avg=1.02s   min=1.01s  med=1.02s   max=1.03s   p(90)=1.02s   p(95)=1.02s   
     iterations.....................: 30      0.978945/s
     vus............................: 1       min=1        max=1
     vus_max........................: 1       min=1        max=1

대략 이런결과가 나오는데 글로만 설명하겠다.

그리고 부하테스트
초당 몇명이상을 부하를 주고 버틸 수 있는지 테스트 해보았다.

그리고 멕시멈이 무엇인지 확인하는 스트레스 테스트를 진행하였고,

마지막으로는 스파이크 테스트를 진행했다.

이 기본 결과를 토대로 index를 설정하고 테스트를 해보았다.

2. 인덱스 테스트
- 쿼리
SELECT *
FROM Screenings s
INNER JOIN Movies m ON s.movie_id = m.movie_id
INNER JOIN Theaters t ON s.theater_id = t.theater_id
WHERE m.title = 'Movie Title 378'
  AND m.genre = 'ACTION'
ORDER BY m.release_date, s.screening_date;

기본적으로 아래와 같은 쿼리였고, 인덱스를 어떻게 설정할까 생각해 보았을 때 처음에는 where 문만 고려했다.
title은 고유의 이름이여서 title을 선두로한 title, genre 복합인댁스를 만들었고, 
*동적 쿼리이기 때문에 앞에 건 인댁스일경우 titile이 들어오지않고 genre만 들어오면 인댁스를 타지 않기 때문에 *
Genre 단일 인덱스도 걸어주었다.

이러고 query 실행결과를 보니 movie단의 index는 잘 타는데 Screening단의 join문에서는 index를 안타는 것을 보았다.
그렇기에 찾아보니 screening단에도 join하는 컬럼에 대해 index를 걸어주면 좋다는 것을 인지했고, 보통 한 테이블당 하나의 index만 타기때문에 이것은 mysql이 효율적인 것을 선택하는 것으로 확인했다. 마지막으로 order by 부분도 인댁스를 걸어주었지만, mysql은 이 인댁스를 선택하지 않았다.

이 인댁스를 걸고 k6로 테스트를 해본 결과 크게 눈에띄는 차이를 보이진 않았지만, tps와 속도 또한 감소되는 것을 볼 수 있었다.

3. 내부캐시, 분산 캐시 redis
config는 자세하게 공부해보지는 않았고, 추후 공부 예정이다. 내부 캐시를 설정하고 분산 캐시를 설정한 후 각각 한번씩 테스트 해본 결과 정말 눈에띄게 속도가 빨라지고 query또한 한번만 불러와 지기때문에 극한의 효율성을 보았다.

내부캐시는 jvm마다 적용되기때문에 멀티모듈에서는 데이터를 공유할 수 없어 분산 캐시를 사용한다는데. 데이터를 공유하지 않는 상황 ex) 복잡한 쿼리를 캐시에 적용하여 쓰는 경우 는 내부캐시 분산캐시 모두 사용이 가능했다.

추후 알아가고싶은 부분은 더 효율적인 index설정과 더 극명하게 나뉘는 성능 테스트 그리고 분산 내부 캐시 config에 대한 내용이다
