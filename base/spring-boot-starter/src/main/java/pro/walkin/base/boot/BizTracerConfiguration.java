package pro.walkin.base.boot;

import org.springframework.context.annotation.Bean;
import pro.walkin.base.boot.component.TTLServletFilter;

public class BizTracerConfiguration {

    @Bean
    public TTLServletFilter ttlServletFilter() {
        return new TTLServletFilter();
    }

}
