package org.ticket.model.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import javax.transaction.NotSupportedException;

public class GenericDAO<T> implements IGenericDAO<T> {

    EntityManager manager = JPAConn.instance();

    public void save(T object) throws PersistenceException {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
    }

    public T search(Class objClass, String fieldName, String argument) throws PersistenceException {
        String query_string = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = \'" + argument + "\'";
        Query query = manager.createQuery(query_string);
        Object object = query.getSingleResult();
        return (T) object;
    }

    public T search(Class objClass, String fieldName, int argument) throws PersistenceException  {
        String query_string = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = " + argument;
        Query query = manager.createQuery(query_string);
        Object object = query.getSingleResult();
        return (T) object;
    }

    public List<T> search(Class objClass) throws PersistenceException  {
        String query_string = "SELECT t FROM " + objClass.getTypeName() + " t";
        Query query = manager.createQuery(query_string);
        // This may return a list of tickets or users
        List<T> objects = query.getResultList();
        return objects;
    }

    /**
     * @brief Updates a field of a class on the
     * @param objClass The class object to be updated.
     * @param searchFieldName
     * @param updateFieldName
     * @param value The new value to be set on fieldName
     * @param pkey The pkey of the row to be updated.
     * @return The new object updated
     */
    public int update(Class objClass, String searchFieldName, String updateFieldName, String value, int pkey)
            throws PersistenceException {

        String query_string = "UPDATE" + objClass.getTypeName() + "t SET" + updateFieldName + " = " + value + "t WHERE "
                + updateFieldName + " = " + pkey;
        Query query = manager.createQuery(query_string);

        return query.executeUpdate();
    }

    public int update(T object) {
        manager.getTransaction().begin();
        manager.refresh(object);
        manager.getTransaction().commit();

        return 1;
    }

    public int delete(T object) throws PersistenceException {
        manager.getTransaction().begin();
        manager.remove(object);
        manager.getTransaction().commit();

        return 1;
    }
}
