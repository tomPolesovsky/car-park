package cz.pa165.carpark.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Configuration of service layer
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Configuration
@ImportResource({"classpath*:application.xml"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }

}
