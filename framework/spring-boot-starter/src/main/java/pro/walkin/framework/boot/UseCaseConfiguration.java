package pro.walkin.framework.boot;

import org.springframework.context.annotation.Bean;
import pro.walkin.framework.api.CommandOperations;
import pro.walkin.framework.api.UseCaseMetas;
import pro.walkin.framework.boot.usecase.UseCaseBeanPostProcessor;

public class UseCaseConfiguration {

    @Bean
    public UseCaseMetas useCaseMetas() {
        return new UseCaseMetas();
    }

    @Bean
    public CommandOperations commandOperations() {
        return new CommandOperations();
    }

    @Bean
    public UseCaseBeanPostProcessor useCaseBeanPostProcessor() {
        return new UseCaseBeanPostProcessor();
    }

}
