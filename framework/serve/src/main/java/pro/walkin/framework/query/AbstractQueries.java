package pro.walkin.framework.query;

import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import pro.walkin.framework.entity.IEntity;
import pro.walkin.framework.exception.BizException;

import java.util.Optional;

public abstract class AbstractQueries<E extends IEntity, R extends BaseQueryDAO<E>> {

    protected R dao;

    @Autowired
    public void setDao(R dao) {
        this.dao = dao;
    }

    public Optional<E> getByObjectKey(String rrn) {
        if (StrUtil.isBlank(rrn)) {
            return Optional.empty();
        }
        return dao.getByObjectKey(rrn);
    }

    public E getByObjectKeyCheckNull(String rrn) {
        return getByObjectKey(rrn).orElseThrow(
                () -> new BizException(StrUtil.format("the entity{} rrn:{} not found data", rrn)));
    }

}
