package pro.walkin.framework.global;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContainer implements InitializingBean, ApplicationContextAware {

    protected static ApplicationContext appContext;

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return appContext.getBean(requiredType);
    }

    public static <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        return appContext.getBeanProvider(requiredType);
    }

    public static <T> T getBean(String beanName) {
        return (T) appContext.getBean(beanName);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return appContext.getBean(name, requiredType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

}
