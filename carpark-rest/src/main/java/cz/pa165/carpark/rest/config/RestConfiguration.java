package cz.pa165.carpark.rest.config;

import cz.pa165.carpark.service.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(value = "cz.pa165.carpark.rest")
public class RestConfiguration {
}
