package cz.pa165.carpark.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import cz.pa165.carpark.data.config.DataConfiguration;
import cz.pa165.carpark.rest.config.RestConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import java.util.List;

@Configuration
@EnableWebMvc
@Import({RestConfiguration.class, DataConfiguration.class})
public class WebConfiguration implements WebMvcConfigurer {

    @Inject
    @Qualifier("restMapper")
    private ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper webObjectMapper = objectMapper.copy();
        webObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        converters.add(new MappingJackson2HttpMessageConverter(webObjectMapper));
    }

}
