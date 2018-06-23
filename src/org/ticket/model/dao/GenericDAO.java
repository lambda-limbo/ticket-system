package org.ticket.model.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.*;

public class GenericDAO<T> implements IGenericDAO<T> {

    EntityManager manager = JPAConn.instance();

    @Override
    public void save(T object) throws PersistenceException {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
    }

    @Override
    public T search(Class objClass, String fieldName, String argument) throws PersistenceException {
        String query_message = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = \'" + argument + "\'";
        Query query = manager.createQuery(query_message);
        Object object = query.getSingleResult();
        return (T) object;
    }

    @Override
    public T search(Class objClass, String fieldName, int argument) throws PersistenceException  {
        String query_message = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = " + argument;
        Query query = manager.createQuery(query_message);
        Object object = query.getSingleResult();
        return (T) object;
    }

    @Override
    public List<T> search(Class objClass) throws PersistenceException  {
        String query_message = "SELECT t FROM " + objClass.getTypeName() + " t";
        Query query = manager.createQuery(query_message);
        List<T> tickets = query.getResultList();
        return tickets;
    }

    @Override
    public void delete(T object) throws PersistenceException {
        manager.getTransaction().begin();
        manager.remove(object);
        manager.getTransaction().commit();
    }
}
