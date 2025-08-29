package com.openclassrooms.estate_api.configuration;

import com.cloudinary.Cloudinary;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

@SpringBootConfiguration
@OpenAPIDefinition(info = @Info(title = "${project.description}", version = "${project.version}"))
public class EstateApiConfig extends DelegatingWebMvcConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper().registerModule(new RecordModule());
    }

    @Bean
    public Cloudinary cloudinary(@Value("${cloudinary.url}") String cloudinaryUrl) {
        return new Cloudinary(cloudinaryUrl);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
    }
}