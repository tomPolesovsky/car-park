package cz.pa165.carpark.persistence.dao;

import javax.persistence.Entity;
import java.util.List;

/**
 * Data access object (dao) is generic api which provides
 * an abstract interface for any kind of persistent storage
 *
 * @param <T> generic type annotated by {@link Entity}
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
public interface Dao<T> {

    /**
     * Find required entity in persistent storage by id
     *
     * @param id unambiguous identification of entity
     * @return entity object or null, if entity was not found
     */
    T find(Long id);

    /**
     * Find all available entities in persistent storage
     *
     * @return list of entities or null,
     * if there were no entities found
     */
    List<T> findAll();

    /**
     * Save new entity to persistent storage
     *
     * @param entity object representing entity,
     * annotation {@link Entity} is required
     */
    void save(T entity);

    /**
     * Update entity in persistent storage
     *
     * @param entity object representing entity,
     * annotation {@link Entity} is required
     */
    void update(T entity);

    /**
     * Delete entity from persistence storage by id
     *
     * @param id unambiguous identification of entity
     */
    void delete(Long id);

}
