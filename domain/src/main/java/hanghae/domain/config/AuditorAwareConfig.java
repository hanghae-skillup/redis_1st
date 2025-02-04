package hanghae.domain.config;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareConfig implements AuditorAware<String> {

    @Override
    public @NonNull Optional<String> getCurrentAuditor() {
        // TODO 시큐리티 Principal 이랑 연동
        return Optional.of("admin");
    }
}
