## [본 과정] 이커머스 핵심 프로세스 구현
[단기 스킬업 Redis 교육 과정](https://hh-skillup.oopy.io/) 을 통해 상품 조회 및 주문 과정을 구현하며 현업에서 발생하는 문제를 Redis의 핵심 기술을 통해 해결합니다.
> Indexing, Caching을 통한 성능 개선 / 단계별 락 구현을 통한 동시성 이슈 해결 (낙관적/비관적 락, 분산락 등)

## ERD

<img src="docs/images/movie.png" width="1000">

### 테이블 구성

- movie : 영화의 기본정보
- theater : 영화관 정보
- screen: 영화관의 상영관 정보
- schedule: 상영관의 일정표
- reservation: 영화예매 정보
- seat: 좌석 정보

## 멀티 모듈 구성도

```vi
├── movie-api/
│   └── interfaces/
│       ├── dto
│       └── controller
│
├── movie-domain/
│   ├── enums
│   ├── exception
│   ├── movie/
│   │   └── domain/
│   │       ├── movie 관련 POJO domain
│   │       ├── dto
│   │       ├── service
│   │       └── repository
│   ├── response
│   └── userAccount
│
├── movie-storage/
│   ├── config
│   ├── movie/
│   │   ├── mapper
│   │   ├── repository/
│   │   │   ├── JpaRepository
│   │   │   └── RepositoryImpl
│   │   └── entity/
│   │       └── MovieEntity
│   └── userAccount
│
└── movie-infrastructures/
    └── ... redis 추가를 위한 모듈
```
- movie-api
  - 외부와 통신을 담당하는 interfaces 영역으로 분리
- movie-domain
  - layered architecture 및 도메인 중심적인 관심사 분리를 위한 clean architecture 구성
- movie-storage
  - 도메인의 DI를 적용하기 위해 persistence layer 분리
  - 추후 디비 구성 변경을 용이하게 하기 위한 구성
  - NoSql을 추가 가능하도록 추가함
- movie-infrastructure
  - redis, kafka 등 인프라를 구성하기 위한 모듈

### 상영중인 응답 api 구성

- url : /api/schedules?theaterId=1
- response
```json
GET http://localhost:8080/api/schedule?theaterId=1

HTTP/1.1 200 

{
  "resultCode": "SUCCESS",
  "result": [
    {
      "id": 1,
      "theater": {
        "id": 1,
        "name": "A영화관"
      },
      "screen": {
        "id": 1,
        "theaterId": 1,
        "name": "1관"
      },
      "movie": {
        "id": 1,
        "title": "범죄도시",
        "releaseDate": "2025-01-01T00:00:00",
        "thumbnailUrl": "http://thumbnailA.png",
        "runningTime": "120",
        "filmRating": "R_15",
        "genre": "ACTION"
      },
      "timeTables": [
        {
          "startDate": "2025-01-08T08:00:00",
          "endDate": "2025-01-08T10:00:00"
        },
        {
          "startDate": "2025-01-08T10:00:00",
          "endDate": "2025-01-08T12:00:00"
        },
        {
          "startDate": "2025-01-08T12:00:00",
          "endDate": "2025-01-08T14:00:00"
        },
        {
          "startDate": "2025-01-08T14:00:00",
          "endDate": "2025-01-08T16:00:00"
        },
        {
          "startDate": "2025-01-08T16:00:00",
          "endDate": "2025-01-08T18:00:00"
        },
        {
          "startDate": "2025-01-08T18:00:00",
          "endDate": "2025-01-08T20:00:00"
        },
        {
          "startDate": "2025-01-08T20:00:00",
          "endDate": "2025-01-08T22:00:00"
        },
        {
          "startDate": "2025-01-08T22:00:00",
          "endDate": "2025-01-08T23:00:00"
        }
      ]
    },

    ...
```



