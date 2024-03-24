package pro.walkin.framework.jpa;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityIEntityRepository<T> extends IEntityRepository<T> {


}
