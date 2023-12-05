package com.epitech.bankserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/home").setViewName("admin/home");
//        registry.addViewController("/").setViewName("admin/home");
        registry.addViewController("/admin/hello").setViewName("admin/hello");
        registry.addViewController("/admin/login").setViewName("admin/login");
    }


}
