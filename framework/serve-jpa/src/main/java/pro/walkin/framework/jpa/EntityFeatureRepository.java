package pro.walkin.framework.jpa;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityFeatureRepository<T> extends FeatureRepository<T, String> {


}
