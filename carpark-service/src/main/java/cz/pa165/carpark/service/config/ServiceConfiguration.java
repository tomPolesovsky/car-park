package cz.pa165.carpark.service.config;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import cz.pa165.carpark.persistence.config.PersistenceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration of service layer
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan(value = "cz.pa165.carpark.service")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        return DozerBeanMapperBuilder.buildDefault();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
