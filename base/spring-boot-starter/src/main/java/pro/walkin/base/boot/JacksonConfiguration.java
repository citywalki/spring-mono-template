package pro.walkin.base.boot;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import pro.walkin.framework.core.JsonKit;

public class JacksonConfiguration {

    @Bean
    public CommandLineRunner registryDefaultObjectMapper(ObjectMapper objectMapper) {
        return args -> JsonKit.setDefaultObjectMapper(objectMapper);
    }

}
