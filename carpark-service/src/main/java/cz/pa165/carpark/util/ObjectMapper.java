package cz.pa165.carpark.util;

import java.util.Collection;
import java.util.List;

public interface ObjectMapper {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

}
