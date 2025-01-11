### 프로젝트 개요
- 목적 : 영화 데이터 관리, 상영 시간표 제공 REST API 구현
- 특징 : 확장성 고려한 멀티 모듈 설계, JPA를 통한 데이터 관리, Docker를 활용한 Database 환경 구성

### 모듈 구성
- common : 공통 DTO, 예외 처리 등 공유되는 코드
- movie-api : 영화관리 및 상영 시간표와 관련된 API를 제공

### 프로젝트 설계
![erd2.png](erd2.png)
- 데이터베이스 관계
  - Movie ↔ Schedule: 1:N
  - Movie ↔ Rating: N:1
