package pro.walkin.framework.boot.usecase;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import pro.walkin.framework.api.Command;
import pro.walkin.framework.api.UseCase;
import pro.walkin.framework.api.UseCaseMeta;
import pro.walkin.framework.api.UseCaseMetas;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class UseCaseBeanPostProcessor implements BeanPostProcessor {

    private UseCaseMetas useCaseMetas;

    @Autowired
    public void setUseCaseMetas(UseCaseMetas useCaseMetas) {
        this.useCaseMetas = useCaseMetas;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UseCase<?, ?>) {
            UseCaseMeta useCaseMeta = new UseCaseMeta();
            useCaseMeta.setCommand(getCommandClass(bean.getClass()));
            useCaseMeta.setOrder(((UseCase<?, ?>) bean).order());
            useCaseMeta.setService(beanName);

            useCaseMetas.add(useCaseMeta);
        }

        return bean;
    }

    private Class<? extends Command<?>> getCommandClass(Class<?> currentClass) {

        Type[] genericInterfaces = currentClass.getGenericInterfaces();

        for (Type genericInterface : genericInterfaces) {
            ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
            if (parameterizedType.getRawType().getTypeName().equals(UseCase.class.getName())) {
                Type type = parameterizedType.getActualTypeArguments()[0];
                if (type instanceof Class) {
                    return (Class<? extends Command<?>>) type;
                }
                if (type instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) type;
                    return (Class<? extends Command<?>>) typeVariable.getBounds()[0];
                }
            }
        }

        return null;
    }

}
