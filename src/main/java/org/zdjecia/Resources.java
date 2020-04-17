package org.zdjecia;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//http://localhost:8080/images/boot.jpg
@Configuration
public class Resources implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("");
    }
}