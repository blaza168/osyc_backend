package com.blaazha.osyc_blazek.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Import({RepositoryConfig.class})
public class AppConfig implements WebMvcConfigurer {
}
