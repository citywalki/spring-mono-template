package pro.walkin.base.boot;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("pro.walkin.base.domain.*")
@ComponentScan(value = {"pro.walkin.base.logic", "pro.walkin.base.integration", "pro.walkin.base.web"})
@EnableJpaRepositories(basePackages = "pro.walkin.base.logic.*.repository",
                       repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class BaseComponentConfiguration {

}
