package pro.walkin.framework.query;

import pro.walkin.framework.entity.IEntity;
import pro.walkin.framework.entity.IPage;

import java.util.List;
import java.util.Optional;

public interface BaseQueryDAO<E extends IEntity> {
    <E extends IEntity> Optional<E> getByObjectKey(String rrn);

    <E extends IEntity> Optional<E> getByRsql(String rsql);


    <E extends IEntity> List<E> findByRsql(String rsql);

    <E extends IEntity> IPage<E> pageByRsql(String rsql);

}
