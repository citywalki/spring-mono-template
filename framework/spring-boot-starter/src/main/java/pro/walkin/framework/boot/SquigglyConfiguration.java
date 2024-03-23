package pro.walkin.framework.boot;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.bohnman.squiggly.filter.SquigglyPropertyFilter;
import com.github.bohnman.squiggly.filter.SquigglyPropertyFilterMixin;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import pro.walkin.framework.web.Result;

public class SquigglyConfiguration {

    @Bean
    public Filter squigglyServletFilter() {
        return new SquigglyRequestFilter();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer squigglyJsonCustomizer() {

        return builder -> {
            var context = new RequestSquigglyContextProvider() {
                @Override
                protected String customizeFilter(String filter, HttpServletRequest request, Class beanClass) {
                    if (filter != null && Result.class.isAssignableFrom(beanClass)) {
                        filter = "msg,status,data[total," + filter + "]";
                    }
                    return filter;
                }
            };

            SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
            simpleFilterProvider.addFilter("squigglyFilter", new SquigglyPropertyFilter(context));

            builder.filters(simpleFilterProvider).mixIn(Object.class, SquigglyPropertyFilterMixin.class);
        };

    }

}
