package cz.pa165.carpark.persistence.dao;

import cz.pa165.carpark.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Specific implementation of {@link Dao} interface
 * Operations with persistent storage are defined by JPA Api
 * For more information {@link EntityManager}
 *
 * @param <T> generic type annotated by {@link Entity}
 * @author Tomáš Polešovský, 487574@mail.muni.cz
 */
public abstract class DaoImpl<T extends BaseEntity> implements Dao<T> {

    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(Long id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + entityClass.getName())
                .getResultList();
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        if (this.find(entity.getId()) == null) {
            throw new EntityNotFoundException("Entity with id "+entity.getId()+" was not found.");
        }
        em.merge(entity);
    }

    @Override
    public void delete(Long id) {
        em.remove(this.find(id));
    }

}
