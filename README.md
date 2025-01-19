### 1. 멀티모듈 & 아키텍쳐 

* movie-adpater : 외부로 부터 들어오는 요청 & 내부에서 외부로 나가는 응답을 처리하는 모듈  
* movie-application : 서비스의 비즈니스 로직을 처리할 수 있는 모듈
* movie-common : 공통으로 사용하는 코드를 관리하는 모듈

### 2. ERD
<img width="1100" alt="Image" src="https://github.com/user-attachments/assets/008c030e-535f-4103-837e-7f0cafb0779e" />

### 3. 실행
* 도커 실행 명령어
> docker compose -f ./docker/docker-compose.yml up -d
* 도커 중지 명령어
> docker compose -f ./docker/docker-compose.yml down

