package cz.pa165.carpark.data.config;

import cz.pa165.carpark.data.SampleDataFacade;
import cz.pa165.carpark.data.SampleDataFacadeImpl;
import cz.pa165.carpark.service.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataFacadeImpl.class})
public class DataConfiguration {

    private SampleDataFacade sampleDataFacade;

    @Inject
    public DataConfiguration(SampleDataFacade sampleDataFacade) {
        this.sampleDataFacade = sampleDataFacade;
    }

    @PostConstruct
    public void loadData() {
        sampleDataFacade.loadData();
    }

}
