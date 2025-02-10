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
![image](https://github.com/user-attachments/assets/b55c39d3-2488-40d9-a6ce-a60cfb507124)
* movie와 screening 관계
  * 1:N 관계 -> 하나의 영화는 여러 상영 정보를 가집니다.
* theater과 screening 관계
  * 1:N 관계 -> 하나의 상영관은 여러 상영 정보를 가집니다.
* theater과 seat 관계
  * 1:N 관계 -> 하나의 상영관은 여러 개의 좌석을 갖습니다.
