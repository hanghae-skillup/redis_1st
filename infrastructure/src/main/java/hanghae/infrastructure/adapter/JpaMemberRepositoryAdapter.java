package hanghae.infrastructure.adapter;

import hanghae.domain.entity.Member;
import hanghae.domain.port.MemberRepository;
import hanghae.infrastructure.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepositoryAdapter implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Optional<Member> findMemberById(Long memberId) {
        return jpaMemberRepository.findById(memberId);
    }
}
