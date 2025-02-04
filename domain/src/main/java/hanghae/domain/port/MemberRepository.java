package hanghae.domain.port;

import hanghae.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findMemberById(Long memberId);
}
