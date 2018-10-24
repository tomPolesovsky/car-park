package cz.pa165.carpark.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @param <T>
 */
public abstract class DaoImpl<T> implements Dao<T> {

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
        return em.createQuery( "from " + entityClass.getName())
                .getResultList();
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Long id) {
        em.remove(this.find(id));
    }

}
