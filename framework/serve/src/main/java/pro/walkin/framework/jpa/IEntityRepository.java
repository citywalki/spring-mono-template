package pro.walkin.framework.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IEntityRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T>,
        QuerydslPredicateExecutor<T> {


}
