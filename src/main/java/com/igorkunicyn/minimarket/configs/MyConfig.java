package com.igorkunicyn.minimarket.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    private static MyConfig myConf;

//    private MyConfig(){}

    public static MyConfig getInstance(){
        if (myConf == null){
            myConf = new MyConfig();
        }
        return myConf;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/jquery/**")
                .addResourceLocations("classpath:META-INF/resources/webjars/jquery/3.3.1/");
        registry.addResourceHandler("/popper/**")
                .addResourceLocations("classpath:META-INF/resources/webjars/popper/js/1.14.3/umd/");
        registry.addResourceHandler("/bootstrap/**")
                .addResourceLocations("classpath:META-INF/resources/webjars/bootstrap/4.1.3/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        if (!registry.hasMappingForPattern("/pictures/**")) {
            registry.addResourceHandler("/pictures/**").addResourceLocations("file:pictures/");
        }
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
