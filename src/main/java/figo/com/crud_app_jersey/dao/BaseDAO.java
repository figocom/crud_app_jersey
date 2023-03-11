package figo.com.crud_app_jersey.dao;

import figo.com.crud_app_jersey.domain.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAO<T extends BaseEntity, ID extends Serializable> {
    protected final EntityManagerFactory emf;
    protected final EntityManager em;
    private final Class<T> persistenceClass;

    @SuppressWarnings( "unchecked" )
    protected BaseDAO() {
        this.emf = Persistence.createEntityManagerFactory("crud_app");
        this.em = emf.createEntityManager();
        this.persistenceClass = (Class<T>) ( ( (ParameterizedType) getClass()
                .getGenericSuperclass() )
                .getActualTypeArguments()[0] );
    }

    public T save( T t ) {
        begin();
        em.persist(t);
        commit();
        return t;
    }

    public T findById( ID id ) {
        begin();
        T t = em.find(persistenceClass, id);
        commit();
        return t;
    }

    public boolean update( T t ) {
        begin();
        em.merge(t);
        commit();
        return true;
    }

    public boolean delete( T t ) {
        em.remove(t);
        return true;
    }

    public boolean deleteById( ID id ) {
        begin();
        boolean delete = em.createQuery("delete from " + persistenceClass.getSimpleName() + " t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate() == 0;
        commit();
        return delete;
    }

    public List<T> findAll() {
        begin();
        Query query = em.createQuery("from " + persistenceClass.getSimpleName());
        System.out.println(query.toString());
        commit();
        return query.getResultList();
    }






    protected void begin() {
        em.getTransaction().begin();
    }

    protected void commit() {
        em.getTransaction().commit();
    }
}