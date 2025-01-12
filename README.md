## [본 과정] 이커머스 핵심 프로세스 구현
[단기 스킬업 Redis 교육 과정](https://hh-skillup.oopy.io/) 을 통해 상품 조회 및 주문 과정을 구현하며 현업에서 발생하는 문제를 Redis의 핵심 기술을 통해 해결합니다.
> Indexing, Caching을 통한 성능 개선 / 단계별 락 구현을 통한 동시성 이슈 해결 (낙관적/비관적 락, 분산락 등)


### 1. 멀티모듈 & 아키텍쳐 

* movie-adpater : 외부로 부터 들어오는 요청 & 내부에서 외부로 나가는 응답을 처리하는 모듈  
* movie-application : 서비스의 비즈니스 로직을 처리할 수 있는 모듈
* movie-common : 공통으로 사용하는 코드를 관리하는 모듈

### 2. ERD
<img width="951" alt="ERD" src="https://github.com/user-attachments/assets/612637dc-ab63-42b4-85e2-971d2f3b1641" />
