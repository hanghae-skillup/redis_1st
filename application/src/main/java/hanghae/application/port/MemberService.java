package hanghae.application.port;

import hanghae.domain.entity.Member;

public interface MemberService {

    // 애플리케이션용, 만약 클라이언트용이 필요하다면 반환 타입이 DTO 인 로직 추가
    Member findMemberById(Long memberId);
}
