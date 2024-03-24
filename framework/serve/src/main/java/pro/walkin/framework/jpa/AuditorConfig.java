package pro.walkin.framework.jpa;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.AuditorAware;
import pro.walkin.framework.tracer.BizBaggageHolder;

import java.util.Optional;

public class AuditorConfig implements AuditorAware<String> {
    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(BizBaggageHolder.getContext().userKey());
    }

}
