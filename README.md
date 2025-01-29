
## GettingStart
### Docker-Compose 구성
```yam
version: "3.7"
services:
  db:
    image: postgres
    restart: always
    ports:
      - "5499:5432"
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: postgres
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql # DDL 및 insert
  app:
    build:
      context: .  # Dockerfile이 있는 디렉토리
      dockerfile: Dockerfile  # 사용할 Dockerfile 지정 (옵션)
    ports:
      - "8999:8080"
    environment:
      SPRING_PROFILES_ACTIVE: dev
```
### Docker-compose 실행
```shell
mkdir test-dbdb-hanghaeho
cd test-dbdb-hanghaeho
git init 
git pull https://github.com/dbdb1114/HangHaeHo
docker-compose -p dbdb1114_hanghaeho up
```
**접속 확인** <br> 
http://localhost:8999



## 데이터베이스 설계
### DB ERD
![스크린샷 2025-01-11 오전 11 09 51](https://github.com/user-attachments/assets/efcb6f9b-2f6d-4604-8172-73ed172cf113)
DB설계에서는 구매나 관리자와 같은 것들은 크게 고려하지 않은 상태로 만들었습니다. 
기초적인 영화관의 기본 설계를 생각하면 만들었고 테이블별 설계에 대한 이야기는 아래에 드리겠습니다. 
### 테이블 및 내부 설계 사항 
**공통**<Br>
CreateAt, creatBy, modifyAt, modifyBy 등의 칼럼을 두고, 생성일 및 생성자, 수정일 및 수정자 칼럼을 두었습니다. 

**Movie**<br>
영화정보 테이블입니다. 고유 번호를 가지고, 제목(현재는 title로 변경했습니다.), 개봉일(open_day), 러닝타임(running_time), 썸네일 사진(thumbnail_src),
장르id(genre_id), 상영등급(rating_id)를 두었습니다. movie 테이블을 설계하면서 장르와 상영등급을 따로 관리하는 것이 맞는지 고민했는데, 실제로
영화 관련 사이트를 보면, 장르에 대한 설명, 상영등급에 관한 설명, 각각에 대한 이미지 등이 따로 관리될 때도 있습니다. 즉, 단순히 장르와 상영등급이 영화와
1대1 매핑 관계처럼 보이지만 이 외의 쓰임도 있을 수 있는 것을 보아 따로 관리하기로 했습니다.

**rating, genre**<br>
상영등급과 장르 테이블입니다. movie테이블 설명드린 것 처럼 별도의 테이블로 준비했습니다. 현재 항해호 서비스에서는 장르명과 등급명 이외에 따로 관리되는 것은
없기 때문에 각각의 id와 name만 부여했습니다. 


**showing**<br>
movie 테이블 다음으로 가장 신경을 많이 쓴 테이블 입니다. 상영 정보는 참조할 내용이 많습니다. 상영관(screen_id), 영화(movie_id), 시작시간(st_time), 종료시간(ed_time) 하나하나 중요한 정보들입니다.
그래서 더더욱 정규화를 지키고자 했습니다. 아직까지 신경이 쓰이는 것은 시간 st_time과 ed_time을 별도 테이블로 관리를 했어야 하는 약간의 아쉬움이 남지만,
그 부분 까지는 좀 과하다는 생각이 들어 진행하진 않았습니다.

**screen**<br>
상영관 테이블 입니다. 지금은 상영관명(name)만을 가지고 있지만 실제로는 아마 대피공간 지도 라던가 대피공간 이미지 등 영화관에 갔을 때 나오는 상영관별로 사용하는
고유 데이터들이 있습니다. 

**seats**<br>
좌석 정보 테이블 입니다. 해당 좌석의 상영관과 좌석의 행(row, A-B-C-D-E),열(number)를 주요 데이터로 가지고 있습니다. 

**ticket**<br>
ticket은 상영 정보(showing_id), 좌석 정보(seat_id), 판매 상태(status) 칼럼을 가지고 있습니다. 차후 추가적인 요구사항을 개발해 나가면서 좀 더 
수정 보완할 부분들이 가장 많이 생기지 않을까 생각이 드는 부분입니다. 



## 아키텍처 및 모듈 구성

### Layered Architecture

LayeredArchitecture는 시스템 설계에 있어 가장 클리셰와 같은 아키텍처 입니다. 이번 프로젝트의 아키텍처를 정하면서 여러가지 아키텍처를 둘러보게 됐습니다. 헥사고날 아키텍처의 포트와 어댑트 개념도 있었고, 클린 아키텍처 개념도 있었습니다. 이 중에서 layered architecutre를 선정하게 된 이유는 가장 분리를 위한 철학이 강하다고 느껴졌습니다. 그럼에도 아키텍처 중에서 고민했던 헥사고날을 왜 하지 않게 됐는지도 함께 설명을 드리겠습니다.

**헥사고날은 변화에 강하다**<br>
이번 프로젝트에서 헥사고날 아키텍처와 레이어드 아키텍처를 가장 고민했습니다. 헥사고날을 둘러봤을 때 제가 느낀점은 **변화에 강한 설계** 라고 느꼈습니다. 물론 모든 설계가 변경과 유지보수를 생각하며 고안되었지만, 각 설계마다 특징과 장점은 다양합니다. 그리고 제가 느낀 헥사고날은 변화에 강하다는 느낌이었습니다.

**항해호 프로젝트는 어떤 변화에 대응해야할까?**<br>
큰 변화가 있을 거라고 예상되지 않습니다. 만약 있다면 데이터를 서빙하는 방식에 있어서는 변화될 수 있겠다고 생각했습다. 그렇다면 그 데이터 서빙의 변경을 어느정도 커버를 해야할까 즉, 그러한 변경 지점이 헥사고날 아키텍처를 사용해야할 정도로 다양하고 변화무쌍한가? 제 생각은 아니었습니다. 그 정도의 확장성과 유지보수성은 헥사고날의 학습곡선과 복잡도를 가져가야할 만큼 의미있지 않았습니다.

**왜 레이어드 아키텍처인가**<br>
다른 아키텍처들도 그렇겠지만 레이어드 아키텍처는 코드를 작성하고, 개발하는 것에 있어 어느정도 강제적인 철학을 가집니다. 예를 들어 의존 관계는 단방향 이라던지 계층별로 독립적으로 존재하며, 인터페이스를 통해서만 통신해야 되는 것 등 어떤 명확한 rule을 제시합니다. 그런 좀 더 명확한 룰은 개발 생산성에 도움이 됩니다. 그래서 종합적으로 봤을 때 이번 항해호 프로젝트에서는 레이어드 아키텍처가 적절하다고 판단했습니다.

### 5개의 멀티모듈

![스크린샷 2025-01-08 오전 9.12.35.png](https://github.com/user-attachments/assets/5b6fd9ab-56f4-4dc3-b3d4-de14888bf064)

항해호 프로젝트는 총 5개의 멀티모듈을 가지기로 결정했습니다. LayerdArchitecture를 기반으로 한 모습이지만 거기서 책임과 역할을 조금 더 세분화하여 총 5개의 모듈로 구분했습니다. 레이어드 아키텍처의 철학을 지키면서 각 **모듈별로 의존 관계를 최소화하고 역할과 책임을 독립적으로 가질 수 있도록** 하였습니다. LayeredArchitecture는 모듈별로 **단방향의 의존 관계**를 가집니다. 이를 확실히 지키고자 의존관계를 아래와 같이 제한하기로 했습니다.

1. app 모듈은 service, core모듈에만 의존합니다.
2. service 모듈은 repo-rds, repo-redis, core모듈에만 의존합니다.
3. repo-rds 모듈은 core모듈에만 의존합니다.
4. repo-reids 모듈은 core모듈에만 의존합니다.

| 의존여부  | app | service | repo-rds | repo-redis | core |
| --- | --- | --- | --- | --- | --- |
| app | O | O | X | X | O |
| service | X | O | O | O | O |
| repo-rds | X | X | O | X | O |
| repo-redis | X | X | X | O | O |
| core | X | X | X | X | O |

### core 모듈

![스크린샷 2025-01-08 오전 10.09.57.png](https://github.com/user-attachments/assets/5c920915-5388-4b2b-a042-eed22edb8e15)

| 의존여부  | app | service | repo-rds | repo-redis | core |
| --- | --- | --- | --- | --- | --- |
| core | X | X | X | X | O |

**core 모듈의 철학은 모든 모듈에서 의존하지만, 이 모듈은 오로지 자바에만 의존합니다.** 해당 모듈은 **exception, dto, enum, util** 과 같은 공통적으로 사용되지만 외부 의존성을 필요로 하지 않는 모듈입니다. 시스템 전반적으로 모든 모듈에서 사용해야 하는 것들을 가지고 있습니다. 흔히 다른 멀티 모듈 프로 젝트에서 종종 보이는 common과 같다고 볼 수 있습니다. 다만, 반드시 필요한 것들을 오로지 자바 코드로만 만들어 낸다는 부분에서 철학이 조금 다릅니다. 그 만큼 단순하면서도 공통적으로 쓰이는 것들을 분리하기 위해 설계된 모듈입니다.

저는 core모듈을 통해 서로 다른 모듈간의 통신을 하게하고, 동일하게 사용되는 타입들을 이곳에 정의하여 사용하여 어떻게 보면 시스템의 전반적임 흐름에 대한 책임을 지게 할 예정입니다.

### repo-rds, repo-redis 모듈

![스크린샷 2025-01-08 오전 10.10.31.png](https://github.com/user-attachments/assets/145d1414-a91a-43d0-9a30-6391c1d86bfe)

| 의존여부 | app | service | repo-rds | repo-redis | core |
| --- | --- | --- | --- | --- | --- |
| repo-rds | X | X | O | X | O |
| repo-redis | X | X | X | O | O |

두 부분은 LayerdArchitecture 의 infrastructure에 해당하는 부분들 입니다. 극장 애플리케이션의 전시 시스템은 데이터가 대개 정형적입니다. 실시간으로 바뀐다기 보다는 하루종일 같은 데이터만 사용할 가능성이 높습니다. 이런 시스템의 경우 redis와 같은 inmemory DB를 사용하는 것이 자연스러운 일이고 매번 RDBMS에서 조회하는 것도 다분히 소모적이라고 생각하여 별도의 모듈로 두 가지를 선택했습니다.

처음에는 rds와 redis 두 Infrastructure를 사용한다는 부분에서 repo-rds, repo-redis, service 이 세 모듈을 하나의 domain이라는 모듈로 가져가는게 낫지 않을까라는 고민도 했습니다. 하지만 그렇게 되면 해당 모듈의 책임이 과중해 집니다. **현재 멀티모듈 프로젝트의 목적은 모듈별 역할과 책임의 분리인데, 그런 목적에서 멀어지게 됩니다.**

그렇다고 repo-rds와 repo-redis를 repo라는 하나의 모듈로 가져가는 것 또한 그다지 좋은 선택은 아닌 것 같았습니다. 이때 발생할 수 있는 문제는 앞서 이야기했던 모듈별 역할과 책임의 분리에도 어긋나기도 하고, repo-rds 모듈은 관리자 페이지를 만들게 될 때 재사용을 할 수 있을 것 같은 느낌이 들었습니다. 모듈별 역할과 책임의 분리의 목적 자체가 **교체 혹은 재사용**인데 repo-rds와 repo-redis를 함께 사용했을 때 보다 별도로 나누는 것이 결국 우리가 원하는 목적지에 조금 더 가까워지는 것이라고 생각했습니다.

### service 모듈

![스크린샷 2025-01-08 오전 10.10.48.png](https://github.com/user-attachments/assets/fc69dbb5-01a8-486f-a795-63c656f05885)

| 의존여부 | app | service | repo-rds | repo-redis | core |
| --- | --- | --- | --- | --- | --- |
| service | X | O | O | O | O |

service 모듈은 전시 시스템의 비즈니스 로직만을 책임집니다. 경우에 따라서 repo-rds 혹은 repo-redis를 나눠서 사용하게 됩니다. 실제 service 코드도 service레이어만 가지게 될 예정입니다.

### app 모듈

![스크린샷 2025-01-08 오전 10.14.03.png](https://github.com/user-attachments/assets/1f71f094-993d-4ece-8dc6-e3feeb6ad877)

| 의존여부 | app | service | repo-rds | repo-redis | core |
| --- | --- | --- | --- | --- | --- |
| app | O | O | X | X | O |

app은 전반적으로 클라이언트와 요청을 주고받는 모듈입니다. 애플리케이션의 수문장 같은 녀석이라고 할 수 있습니다. 여기서 filter, interceptor, controller, security 등 요청 처리, 전처리, 후처리 모든 것이 들어갈 곳 입니다.

### 외부 통신을 해야한다면?

여기서 의문이 있었습니다. 만약 pub/sub 구조의 통신을 또 지원해야한다면 어떻게 해야할까? Redis, rabitmq, kafka 등..

![스크린샷 2025-01-08 오전 11.23.22.png](https://github.com/user-attachments/assets/ab5b660b-1e8c-4986-8d44-fe085c4bbc4a)

명확하진 않지만 아마 이런 구조가 되지 않을까 싶습니다. event를 처리하는 모듈을 하나 만들어서 거기서 관리를 하게 할 것이고 어떤 도메인 관련된 job이 생긴다면 service레이어에게 위임을 하는 그런 방향으로 처리를 할 것 같습니다.

### core가 과중해진다면?

현재는 exception, dto, util 등의 패키지들을 둘 목적이지만, 만약 해당 객체가 너무 많아지거나 어쩔 수 없이 core의 역할을 하면서도 외부 의존성을 필요로 한다면 이렇게도 될 수 있을 것 같습니다.

![스크린샷 2025-01-08 오전 11.26.11.png](https://github.com/user-attachments/assets/036e8c14-4300-4f0b-adc3-603580e30928)

네이밍은 조금 더 신경 쓰겠지만 아무튼 이렇게 두 개로 나눠질 수 있지 않을까 싶습니다. 그땐 조금 더 세부적인 책임과 역할이 생긴 상태겠지요
