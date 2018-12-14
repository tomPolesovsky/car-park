package cz.pa165.carpark.web;

import cz.pa165.carpark.rest.security.DefaultRestFilter;
import cz.pa165.carpark.rest.security.jwt.JwtTokenFilter;
import cz.pa165.carpark.web.config.WebConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/rest/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new DefaultRestFilter(), new JwtTokenFilter()};
    }

}
