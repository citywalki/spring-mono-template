package pro.walkin.framework.jpa;

import jakarta.annotation.Nonnull;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.history.RevisionRepository;

public class CustomRepositoryFactoryBean<T extends RevisionRepository<S, ID, N>, S, ID,
        N extends Number & Comparable<N>> extends EnversRevisionRepositoryFactoryBean<T, S, ID, N> {

    private RegisterEntityRepositoryPostProcessor entityRepositoryPostProcessor =
            new RegisterEntityRepositoryPostProcessor();

    public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }


    @Nonnull
    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        RepositoryFactorySupport repositoryFactorySupport = super.doCreateRepositoryFactory();

        repositoryFactorySupport.addRepositoryProxyPostProcessor(entityRepositoryPostProcessor);

        return repositoryFactorySupport;
    }

}
