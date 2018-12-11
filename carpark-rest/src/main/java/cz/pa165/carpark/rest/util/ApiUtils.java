package cz.pa165.carpark.rest.util;

import java.util.function.Supplier;

public class ApiUtils {

    public static <T extends RuntimeException> void notNull(Object o, Supplier<T> supplier) {
        if (o == null) {
            throw supplier.get();
        }
    }

}
