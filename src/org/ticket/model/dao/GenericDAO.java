package org.ticket.model.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import javax.transaction.NotSupportedException;

public class GenericDAO<T> implements IGenericDAO<T> {

    EntityManager manager = JPAConn.instance();
    
    /**
     * @brief Saves an object of type T to the database
     * @param object
     * @throws PersistenceException
     */
    public void save(T object) throws PersistenceException {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
    }
    
    /**
     * @brief Searches for an object with the given params
     * @param objClass
     * @param fieldName
     * @param argument
     * @return The retrieved object
     * @throws PersistenceException
     */
    public T search(Class objClass, String fieldName, String argument) throws PersistenceException {
        String query_string = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = \'" + argument + "\'";
        Query query = manager.createQuery(query_string);
        Object object = query.getSingleResult();
        return (T) object;
    }
    
    /**
     * @brief
     * @param objClass
     * @param fieldName
     * @param argument
     * @return A single object of type T
     * @throws PersistenceException
     */
    public T search(Class objClass, String fieldName, int argument) throws PersistenceException  {
        String query_string = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = " + argument;
        Query query = manager.createQuery(query_string);
        Object object = query.getSingleResult();
        return (T) object;
    }
    
    /**
     * @brief Searches for a given object
     * @param objClass
     * @return A list of objects corresponding to the search
     * @throws PersistenceException
     */
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
     * @param updateFieldName
     * @param value The new value to be set on fieldName
     * @param pkey The pkey of the row to be updated.
     * @return The ID of the updated object
     */
    public int update(Class objClass, String updateFieldName, String value, int pkey)
            throws PersistenceException {


        String query_string = "UPDATE " + objClass.getTypeName() + " SET " + updateFieldName + " = " + value +
                " WHERE " + updateFieldName + " = " + pkey;
        Query query = manager.createQuery(query_string);

        manager.getTransaction().begin();
        int ret = query.executeUpdate();
        manager.getTransaction().commit();

        return ret;
    }
    
    /**
     * Updates object
     * @param object
     * @return
     */
    public int update(T object) {
        manager.getTransaction().begin();
        manager.refresh(object);
        manager.getTransaction().commit();

        return 1;
    }
    
    /**
     * @brief Deletes object of type T
     * @param object
     * @return
     * @throws PersistenceException
     */
    public int delete(T object) throws PersistenceException {
        manager.getTransaction().begin();
        manager.remove(object);
        manager.getTransaction().commit();

        return 1;
    }
}
