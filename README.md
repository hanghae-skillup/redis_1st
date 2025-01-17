## Multi Module
- adapter : 어댑터 모듈
- application : 포트 모듈
- domain : 도메인 모듈
- api : 웹 어플리케이션 진입점 모듈

## Entity Relationship Diagram
<img width="822" alt="redis_erd" src="https://github.com/user-attachments/assets/e29a1f00-8f2c-40bf-99df-749d741025e9" />


### Movie (영화) 테이블
- 영화의 기본 정보를 저장하는 테이블
- 컬럼 정보
    - rating : 영화의 관람 등급
        - 거의 변동성이 없으므로 enum 으로 관리해주었습니다.
    - running_time : 영화의 상영 시간 (분 단위)
    - genre : 영화 장르
        - 거의 변동성이 없으므로 eunm 으로 관리해주었습니다.
     
### Theater(극장) 테이블
- 극장의 정보를 저장하는 테이블

### ShowTime(상영 시간) 테이블
- 특정 영화의 상영 정보를 저장하는 테이블
- 컬럼 정보
  - movie_id : 상영 영화의 식별자
   - Movie 테이블의 id 를 참조
  - theater_id : 상영 극장의 식별자
   - Theater 테이블의 id 를 참조
  - started_at : 상영 시작 시간
  - ended_at : 상영 종료 시간
 
### 테이블 간 관계
- Movie <-> ShotTime
    - 1:N 관계로 한 영화는 여러 상영 정보를 가질 수 있습니다.
- Theater <-> ShowTime
    - 1:N 관계로 한 극장은 여러 상영 정보를 가질 수 있습니다.  
