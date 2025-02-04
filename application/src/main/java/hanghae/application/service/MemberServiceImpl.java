package hanghae.application.service;

import hanghae.application.port.MemberService;
import hanghae.domain.entity.Member;
import hanghae.domain.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("No member found with id: " + memberId));
    }
}
