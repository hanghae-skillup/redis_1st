## [1주차] 멀티 모듈 구성 및 요구사항 구현
## 내용
### Doamin
  - Movie
    - Long id PK
    - String title
    - String grade
    - String genre
    - Integer runningTime
    - Date releaseDate
    - String thumbnailUrl 
  - Theater 
    - Long id PK 
    - String name 
  - Schedule
    - Long id PK 
    - DateTime startTime 
    - DateTime endTime


### 멀티모듈 구성
  - api : 외부 통신 레이어
  - application : 서비스 레이어
  - domain : 도메인 레이어
  - infra : 인프라 레이어
  
### 발생했던 문제와 해결 과정을 남겨 주세요.
- 멀티모듈 구성에 의존성 오류에서 좀 애를 먹었습니다.
- 개인적인 시간이 없는 이슈가 가능 문제 였습니다. 다음주 부턴 시간확보를 더 많이 해보겠습니다.

### 리뷰 포인트
- 도메인 분리가 잘 되었는지 궁금 합니다.
- 도메인들이 JPA 연관관계를 잘 맺었는지랑 꼭 맺어야 하는지 궁금합니다 (그냥 필요할 때마다 조회를 따로하는건 안되는지 궁금합니다.)



