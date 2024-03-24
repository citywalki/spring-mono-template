package pro.walkin.framework.query;

import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import pro.walkin.framework.entity.IEntity;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.jpa.IEntityRepository;

import java.util.Optional;

public class AbstractQueries<E extends IEntity, R extends IEntityRepository<E>> {

    protected R repository;

    public Optional<E> findById(String id) {
        if (StrUtil.isBlank(id)) {
            return Optional.empty();
        }
        return repository.findById(id);
    }

    public E findByIdCheckNull(String id) {
        return findById(id).orElseThrow(
                () -> new BizException(StrUtil.format("the entity{} rrn:{} not found data", id)));
    }

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

}
