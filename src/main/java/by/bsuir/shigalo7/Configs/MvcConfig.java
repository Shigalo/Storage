package by.bsuir.shigalo7.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"by.bsuir.shigalo7.Controllers"})
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("homepage");
        registry.addViewController("/test").setViewName("homepage");
        registry.addViewController("/login").setViewName("accountWork/login");
        registry.addViewController("/registration").setViewName("accountWork/registration");
        registry.addViewController("/tours").setViewName("tours");
        registry.addViewController("/flights").setViewName("flights/flights");
        registry.addViewController("/flights/add").setViewName("flights/newFlight");
        registry.addViewController("/tours").setViewName("tours/tours");
        registry.addViewController("/tours/add").setViewName("tours/newTour");
   }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/content/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
