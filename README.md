## [본 과정] 이커머스 핵심 프로세스 구현

### Multi Module
* module-movie: 영화와 관련된 작업을 합니다.
* module-theater: 상영관과 관련된 작업을 합니다.
* module-screening: 상영 정보와 관련된 작업을 합니다.
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

* leaseTime: 응답시간이 10초 정도 걸려 10초로 설정했습니다.
* waitTime: 설정한 leaseTime보다 좀 더 기다릴 수 있도록 설정했습니다.

[분산 락 테스트 보고서](https://alkaline-wheel-96f.notion.site/187e443fee68800cbbcef4041b8d55b8)
