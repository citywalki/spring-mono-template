package pro.walkin.framework.boot;

import org.springframework.context.annotation.Bean;
import pro.walkin.framework.global.SpringContainer;
import pro.walkin.framework.service.VersionControlService;

public class FrameworkConfiguration {

    @Bean
    public VersionControlService versionControlService() {
        return new VersionControlService();
    }

    @Bean
    public SpringContainer springContainer() {
        return new SpringContainer();
    }


}
