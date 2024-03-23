package pro.walkin.framework.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface FeatureRepository<T, ID> extends Repository<T, ID>, ListPagingAndSortingRepository<T, ID>,
        QueryByExampleExecutor<T>, JpaSpecificationExecutor<T> {

    List<T> findAll();

    List<T> findAllByObjectKeyIn(Iterable<ID> ids);

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findByObjectKey(ID rrn);

    boolean existsByObjectKey(ID rrn);

    long count();

    void deleteByObjectKey(ID rrn);

    void delete(T entity);

    void deleteAllByObjectKeyIn(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

}
