package cz.pa165.carpark.dao;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface Dao<T> {

    /**
     *
     * @param id
     * @return
     */
    T find(Long id);

    /**
     *
     *
     * @return
     */
    List<T> findAll();

    /**
     *
     * @param entity
     */
    void save(T entity);

    /**
     *
     * @param entity
     */
    void update(T entity);

    /**
     *
     * @param id
     */
    void delete(Long id);

}
