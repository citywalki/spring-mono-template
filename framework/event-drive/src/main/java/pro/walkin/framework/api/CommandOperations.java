package pro.walkin.framework.api;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Comparator;
import java.util.List;

public class CommandOperations implements InitializingBean, ApplicationContextAware {

    public static ApplicationContext applicationContext;

    private static CommandOperations INSTANCE;

    private final Comparator<UseCaseMeta> useCaseComparator = Comparator.comparingInt(UseCaseMeta::getOrder);

    private UseCaseMetas useCaseMetas;

    public static CommandOperations get() {
        if (INSTANCE == null) {
            INSTANCE = applicationContext.getBean(CommandOperations.class);
        }
        return INSTANCE;
    }

    @Autowired
    public void setUseCaseMetas(UseCaseMetas useCaseMetas) {
        this.useCaseMetas = useCaseMetas;
    }

    public <C extends Command<? extends R>, R> R fire(final C command) {
        List<UseCaseMeta> useCaseMetaList = useCaseMetas.getUseCases(command.getClass());

        for (UseCaseMeta useCaseMeta : useCaseMetaList) {
            UseCase<C, R> useCase = applicationContext.getBean(useCaseMeta.getService(), UseCase.class);
            if (useCase.predicate(command)) {
                return useCase.handle(command);
            }
        }

        throw new IllegalStateException("command not handle");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommandOperations.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

}
