package pro.walkin.framework.jpa;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityRepositoryRegister {
    private static final Map<String, Class<? extends Repository<?, ?>>> entityRepository = new ConcurrentHashMap<>();

    public static void register(RepositoryInformation repositoryInformation) {
        Class<?> entityType = repositoryInformation.getDomainType();
        Class<? extends Repository<?, ?>> repositoryInterface =
                (Class<? extends Repository<?, ?>>) repositoryInformation.getRepositoryInterface();

        EntityRepositoryRegister.entityRepository.putIfAbsent(entityType.getSimpleName(), repositoryInterface);
    }

    public static <R extends Repository<?, ?>> Class<R> get(String entity) {

        return (Class<R>) entityRepository.get(entity);
    }

}
