package cz.pa165.carpark.web.config;

import cz.pa165.carpark.data.config.DataConfiguration;
import cz.pa165.carpark.rest.config.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Import({RestConfiguration.class, DataConfiguration.class})
public class WebConfiguration implements WebMvcConfigurer {
}
