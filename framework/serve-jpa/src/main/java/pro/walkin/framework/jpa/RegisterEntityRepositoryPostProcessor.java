package pro.walkin.framework.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

@RequiredArgsConstructor
public class RegisterEntityRepositoryPostProcessor implements RepositoryProxyPostProcessor {

    @Override
    public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {
        EntityRepositoryRegister.register(repositoryInformation);

    }

}
