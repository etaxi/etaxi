package lv.etaxi.config;

/**
 * Created by Aleks on 10.05.2016.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lv.etaxi"})
public class WebMVCConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*")
                .addResourceLocations("/index.html");

        registry.addResourceHandler("/customer/staticRes/**")
                .addResourceLocations("/customer/staticRes/");

        registry.addResourceHandler("/taxi/staticRes/**")
                .addResourceLocations("/taxi/staticRes/");

        registry.addResourceHandler("/staticRes/**")
                .addResourceLocations("/staticRes/");

//        registry.addResourceHandler("/staticRes/css/**")
//                .addResourceLocations("/staticRes/css/");
//
//        registry.addResourceHandler("/staticRes/fonts/**")
//                .addResourceLocations("/staticRes/fonts/");
//
//        registry.addResourceHandler("/staticRes/img/**")
//                .addResourceLocations("/staticRes/img/");
//
//        registry.addResourceHandler("/staticRes/js/**")
//                .addResourceLocations("/staticRes/js/");

    }
}