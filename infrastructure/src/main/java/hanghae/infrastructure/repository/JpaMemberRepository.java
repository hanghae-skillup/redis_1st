package hanghae.infrastructure.repository;

import hanghae.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
}
