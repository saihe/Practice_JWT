package ksaito.practice.jwt.config;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(converter());
    addDefaultHttpMessageConverters(converters);
  }

  @Bean
  public HttpMessageConverter converter() {
    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
      .json()
      .propertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy())
      .build();
    return new MappingJackson2HttpMessageConverter(objectMapper);
  }
}
