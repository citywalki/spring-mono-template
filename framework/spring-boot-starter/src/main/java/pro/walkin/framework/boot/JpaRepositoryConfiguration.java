package pro.walkin.framework.boot;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import pro.walkin.framework.jpa.AuditorConfig;

import static com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module.Feature.*;

public class JpaRepositoryConfiguration {

    @Bean
    public AuditorConfig autoConfiguration() {
        return new AuditorConfig();
    }


    @Bean
    public com.fasterxml.jackson.databind.Module javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public com.fasterxml.jackson.databind.Module hibernateModule() {
        return new Hibernate6Module()
                .disable(USE_TRANSIENT_ANNOTATION)
                .enable(WRITE_MISSING_ENTITIES_AS_NULL)
                .enable(FORCE_LAZY_LOADING);
    }

}
