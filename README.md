## [본 과정] 이커머스 핵심 프로세스 구현

### Multi Module
* module-movie: 영화와 관련된 작업을 합니다.
* module-theater: 상영관과 관련된 작업을 합니다.
* module-screening: 상영 정보와 관련된 작업을 합니다.
* module-reservation: 예약과 관련된 작업을 합니다.
* module-userr: 회원 정보와 관련된 작업을 합니다.
* module-common: Auditing 등 모든 모듈에 적용될 작업을 합니다.
* module-api: 현재 상영 중인 영화 조회 API 등 영화, 상영관, 상영 정보 외의 api와 관련된 작업을 합니다.

### Architecture
* Layered Architecture를 사용하여 계층별로 책임을 분리하여 진행했습니다.

### Table Design
![image](https://github.com/user-attachments/assets/1310bde1-2059-46d4-97ac-d5e3a1f478e0)

* movie와 screening 관계
  * 1:N 관계 -> 하나의 영화는 여러 상영 정보를 갖습니다.
* theater과 screening 관계
  * 1:N 관계 -> 하나의 상영관은 여러 상영 정보를 갖습니다.
* theater과 seat 관계
  * 1:N 관계 -> 하나의 상영관은 여러 개의 좌석을 갖습니다.
* screening과 reservation 관계
    * 1:N 관계 -> 하나의 상영 정보는 여러 개의 예약을 갖습니다.
* reservation과 seat 관계
  * 1:N 관계 -> 하나의 예약은 여러 개의 좌석을 가질 수 있습니다.
* user과 reservation 관계
    * 1:N 관계 -> 한 명의 회원은 여러 개의 예약을 할 수 있습니다.

### 성능 테스트 보고서
* 캐싱할 데이터: API 응답 형식인 **ScreeningDto**

[성능 테스트 보고서](https://alkaline-wheel-96f.notion.site/180e443fee6880caac97deb79ed284d9)

* leaseTime: http_req-duration의 avg값은 44.1ms이고 max가 1.27s기 때문에 max 값까지 다룰 수 있도록 2초로 설정했습니다.
* waitTime: leaseTime 보다 약간 길게 두어 4초로 설정했습니다.

[분산 락 테스트 보고서](https://alkaline-wheel-96f.notion.site/187e443fee68800cbbcef4041b8d55b8)


### Jacoco Report
* module-common
  ![module-common](https://github.com/user-attachments/assets/2d0b9445-4f8f-4d72-be15-62b2e00a74f2)

* module-reservation
  ![module-reservation](https://github.com/user-attachments/assets/ffccbf18-362d-4131-bbb8-331419977791)

* module-screening
  ![mocule-screening](https://github.com/user-attachments/assets/d958a296-e285-468f-9dc4-78c939a2ca5a)
  
* module-api
  ![5주차 module-api](https://github.com/user-attachments/assets/188c3f25-048c-42a8-afaf-b6e4c8f1dca8)

