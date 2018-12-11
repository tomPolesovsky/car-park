package cz.pa165.carpark.persistence.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({"classpath*:application.xml"})
@ComponentScan(value = "cz.pa165.carpark.persistence")
public class PersistenceConfiguration {
}
