package pro.walkin.framework.queries;

import org.dromara.hutool.core.text.StrUtil;
import pro.walkin.framework.entity.NamedEntity;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.jpa.NamedEntityFeatureRepository;

import java.util.Optional;

public abstract class AbstractNamedQueries<E extends NamedEntity, R extends NamedEntityFeatureRepository<E>> extends AbstractQueries<E, R> {

    public Optional<E> findByObjectId(String name) {
        return repository.findByObjectId(name);
    }

    public boolean existsByObjectId(String objectId) {
        return repository.existsByObjectKey(objectId);
    }

    public E getByObjectId(final String name) {
        return findByObjectId(name).orElseThrow(
                () -> new BizException(StrUtil.format("the name:{} not found data", name)));
    }

    public String getNameByRrn(final String name) {
        return getByObjectKey(name).getObjectId();
    }

}
