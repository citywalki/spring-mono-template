package pro.walkin.framework.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import pro.walkin.framework.entity.NamedEntity;

import java.util.Optional;

@NoRepositoryBean
public interface NamedEntityFeatureRepository<T extends NamedEntity> extends EntityFeatureRepository<T> {

    Optional<T> findByObjectId(String name);

    boolean existsByObjectId(String objectId);

}
