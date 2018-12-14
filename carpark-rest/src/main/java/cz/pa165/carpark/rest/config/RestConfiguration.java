package cz.pa165.carpark.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.pa165.carpark.service.config.ServiceConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@Import(value = {ServiceConfiguration.class})
@ComponentScan(value = "cz.pa165.carpark.rest")
public class RestConfiguration {

    @Bean("restMapper")
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
