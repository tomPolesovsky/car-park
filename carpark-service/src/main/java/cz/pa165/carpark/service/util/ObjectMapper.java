package cz.pa165.carpark.service.util;

import java.util.Collection;
import java.util.List;

/**
 * Object mapper for facade layer
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public interface ObjectMapper {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

}
