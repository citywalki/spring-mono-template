package pro.walkin.framework.queries;

import io.github.perplexhub.rsql.RSQLJPASupport;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Setter;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pro.walkin.framework.entity.BaseEntity;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.jpa.EntityFeatureRepository;

import java.util.List;
import java.util.Optional;

public class AbstractQueries<E extends BaseEntity, R extends EntityFeatureRepository<E>> {

    @Setter(onMethod_ = {@Autowired})
    protected R repository;

    public Optional<E> findByObjectKey(String rrn) {
        if (StrUtil.isBlank(rrn)) {
            return Optional.empty();
        }
        return repository.findByObjectKey(rrn);
    }

    public E getByObjectKey(String rrn) {
        return findByObjectKey(rrn).orElseThrow(
                () -> new BizException(StrUtil.format("the entity rrn:{} not found data", rrn)));
    }

    public Page<E> page(String search, @Nullable String sort, Pageable pageable) {
        return repository.findAll(buildCondition(search, sort), pageable);
    }

    public List<E> list(String search, @Nullable String sort) {
        return repository.findAll(buildCondition(search, sort));
    }

    public Optional<E> one(String search) {
        return repository.findOne(RSQLJPASupport.toSpecification(search));
    }

    public Specification<E> buildCondition(@Nonnull String search, @Nullable String sort) {
        var condition = RSQLJPASupport.<E>toSpecification(search);
        if (StrUtil.isNotEmpty(sort)) {
            condition = condition.and(RSQLJPASupport.toSort(sort));
        }
        return condition;
    }

}
