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



Lock

우선 좌석 예약 API를 다음과 같이 구현했다.

1. client에게 상영Id, 좌석번호, 표갯수를 받는다.
2. server에서는 SeatReservation에서 client id로 최대 5자리 까지 예약가능한것을 확인한다.
3. request로 들어온 좌석번호 + 표갯수가 이미 저장되어있는 좌석인지 확인한다.
4. 모든 조건이 만족하면 예약을한다.

다음과 같은 API는 유저 domain, 상영 domain, 좌석 domain이 모두 사용되기때문에 
하나의 service에서 로직을 구현할 경우 service가 service를 참조하는 경우가 생겨
과거에 나눈 application 계층에서 구현을 하였다.

user에 대한 정보는 userService에서 , 상영에대한 대한 정보는 screeningService에서 , 좌석에대한 로직은 seatReservationService에서 구현을 하여 Facade계층은 사용만 하게끔 역할을 줄여주었다.

Lock1. 낙관적 lock
낙관적 lock은 version정보를 두어 update시에 version정보가 달라질 경우 update를 실패하게하여 동시성을 제어하는 방법이다. 하지만 나는 사용하지 못했다. 

이유: 좌석을 미리 만들어놓고 좌석상태를 update하는 방식이 아닌 사용자가 예약할경우 좌석에대한 데이터를 insert하는 방식을 사용했기 떄문이다.

해결: 상영id와 좌석 번호를 유니크키로 묶어서 중복된 좌석이 못들어오게 설계를 하였다.

Lock2. 비관적 lock

해당 좌석이 비어있는지 확인하는 query에 비관적 베타락을 걸어서 조회와 쓰기 모두 불가능하게 해서 순차적으로 처리하게 하는 방법이다. 이 방식은 사용이 가능했다.

결론: 사실상 한번 실패할경우 재시도 로직을 구현하지 않을 경우에 낙관적 lock이 효율이 좋다고 알고있다. 다음과 같은 상황도 좌석예약을 실패하면 그냥 실패로 끝내면 되기때문에 낙관적락이 더 효율이 좋을 것이라고 생각한다.

Lock3. 분산락  Aop형 
Aop기능을 사용하여 분산락을 구현한 것이다.
메서드위에 Aop annotation을 달아 정보를 제어 service로 보내서 처리하는 방법이다.


Lock3. 분산락 함수형
비슷한 로직이지만 함수형으로 처리를 하는것이였다.

k6로 성능테스트를 했을 경우
onnection reset by peer"

     ✗ status is 200
      ↳  99% — ✓ 1091 / ✗ 9
     ✗ response time < 500ms
      ↳  96% — ✓ 1062 / ✗ 38

     checks.........................: 97.86% 2153 out of 2200
     data_received..................: 141 kB 13 kB/s
     data_sent......................: 224 kB 21 kB/s
     http_req_blocked...............: avg=204.24µs min=1µs    med=4µs     max=3.19ms   p(90)=648.2µs p(95)=1.84ms  
     http_req_connecting............: avg=169.85µs min=0s     med=0s      max=2ms      p(90)=604.2µs p(95)=1.8ms   
     http_req_duration..............: avg=57.26ms  min=1.77ms med=18.19ms max=648.52ms p(90)=80.87ms p(95)=429.95ms
       { expected_response:true }...: avg=57.72ms  min=3.61ms med=18.31ms max=648.52ms p(90)=83.96ms p(95)=431.8ms 
     http_req_failed................: 0.81%  9 out of 1100
     http_req_receiving.............: avg=119.9µs  min=0s     med=77µs    max=1.73ms   p(90)=242.1µs p(95)=367.09µs
     http_req_sending...............: avg=18.49µs  min=4µs    med=12µs    max=1.07ms   p(90)=33µs    p(95)=44µs    
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s      max=0s       p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=57.12ms  min=1.76ms med=18.04ms max=648.37ms p(90)=80.74ms p(95)=429.73ms
     http_reqs......................: 1100   102.470193/s
     iteration_duration.............: avg=1.05s    min=1s     med=1.01s   max=1.65s    p(90)=1.08s   p(95)=1.43s   
     iterations.....................: 1100   102.470193/s
     vus............................: 110    min=110          max=110
     vus_max........................: 110    min=110          max=110

connection reset by peer"

     ✗ status is 200
      ↳  99% — ✓ 1091 / ✗ 9
     ✗ response time < 500ms
      ↳  96% — ✓ 1063 / ✗ 37

     checks.........................: 97.90% 2154 out of 2200
     data_received..................: 141 kB 13 kB/s
     data_sent......................: 224 kB 21 kB/s
     http_req_blocked...............: avg=145.92µs min=1µs    med=4µs     max=2.91ms   p(90)=466µs   p(95)=1.27ms  
     http_req_connecting............: avg=103.11µs min=0s     med=0s      max=1.81ms   p(90)=320µs   p(95)=1.02ms  
     http_req_duration..............: avg=56.08ms  min=462µs  med=12.85ms max=642.38ms p(90)=84.55ms p(95)=422.2ms 
       { expected_response:true }...: avg=56.54ms  min=3.67ms med=13.16ms max=642.38ms p(90)=90ms    p(95)=424.49ms
     http_req_failed................: 0.81%  9 out of 1100

Aop가 아주약간 빨랐지만 큰 차이는 못봤다.


결론 
2. 함수형과 AOP의 장단점

2.1. AOP 기반

장점
	1.	코드 간결성:
	•	커스텀 애노테이션(@DistributedLock)을 통해 락 로직과 비즈니스 로직을 분리.
	•	락이 필요한 메서드에 애노테이션만 추가하면 되므로 코드 중복 감소.
	2.	가독성 향상:
	•	메서드에서 락 로직을 직접 호출하지 않아 비즈니스 로직이 더 명확해짐.
	3.	확장성:
	•	재사용성이 높고, 공통으로 처리할 로직이 많을수록 유리.

단점
	1.	구현 복잡성:
	•	AOP를 설정하고 애노테이션 및 Aspect 클래스를 구현해야 함.
	2.	유연성 부족:
	•	락 로직을 메서드 호출마다 다르게 설정해야 할 경우 구현이 복잡.
	3.	디버깅 어려움:
	•	AOP로 동작하는 로직은 런타임에 적용되므로 디버깅이 어려울 수 있음.

2.2. 함수형 기반

장점
	1.	유연성:
	•	락 로직을 호출하는 시점에서 자유롭게 커스터마이징 가능.
	•	동일 메서드라도 다양한 락 설정(예: 키, TTL)을 적용할 수 있음.
	2.	명시적 동작:
	•	락 로직이 명시적으로 작성되므로 디버깅과 추적이 용이.

단점
	1.	코드 중복:
	•	락 로직을 매번 명시적으로 작성해야 하므로 코드가 길어지고 중복될 가능성이 높음.
	2.	가독성 저하:
	•	락 로직과 비즈니스 로직이 혼재되어 가독성이 떨어짐.
	3.	재사용성 낮음:
	•	공통 락 로직을 별도로 관리하기 어려움.

