package cz.pa165.carpark.util;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperImpl {

    private Mapper dozer;

    @Inject
    public ObjectMapperImpl(Mapper dozer) {
        this.dozer = dozer;
    }

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        return objects.stream()
                .map(item -> dozer.map(item, mapToClass))
                .collect(Collectors.toList());
    }

    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }

}
