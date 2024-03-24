package pro.walkin.base.boot;

import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pro.walkin.base.web.BaseFeatureRest;

public class BasicWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api/base", c -> c.isAnnotationPresent(BaseFeatureRest.class));
    }

}
