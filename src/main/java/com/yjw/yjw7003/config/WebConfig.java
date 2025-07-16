package com.yjw.yjw7003.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/uploads/**")
        .addResourceLocations("file:uploads");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    String[] allowedOrigins = {
        "https://yjw7003.info"
    };

    registry.addMapping("/**")
        .allowedOrigins(allowedOrigins) // 허용할 출처
        .allowedMethods("GET", "POST") // 허용할 HTTP method
        .allowCredentials(true) // 쿠키 인증 요청 허용
        .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
  }

}
