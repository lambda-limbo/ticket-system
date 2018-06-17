package org.ticket.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class GenericDAO<T> implements iGenericDAO<T> {

    EntityManager manager = JPAConn.instance();

    @Override
    public void save(T object) {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
    }

    @Override
    public T search(Class objClass, String fieldName, String argument) {
        String query_message = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = \'" + argument + "\'";
        Query query = manager.createQuery(query_message);
        Object object;
        try {
            object = query.getSingleResult();
        }
        catch (NoResultException no) {
            System.out.println("No results found.");
            object = null;
        }
        catch (NonUniqueResultException no) {
            System.out.println("More than one result found.");
            object = null;
        }
        return (T) object;
    }

    @Override
    public T search(Class objClass, String fieldName, int argument) {
        String query_message = "SELECT t FROM " + objClass.getTypeName() + " t WHERE " + fieldName + " = " + argument;
        Query query = manager.createQuery(query_message);
        Object object;
        try {
            object = query.getSingleResult();
        }
        catch (NoResultException no) {
            System.out.println("No results found.");
            object = null;
        }
        catch (NonUniqueResultException no) {
            System.out.println("More than one result found.");
            object = null;
        }
        return (T) object;
    }

    @Override
    public List<T> search(Class objClass) {
        String query_message = "SELECT t FROM " + objClass.getTypeName() + " t";
        Query query = manager.createQuery(query_message);
        List<T> objectList;
        try {
            objectList = query.getResultList();
        }
        catch (NoResultException no) {
            System.out.println("No results found.");
            objectList = null;
        }
        return objectList;
    }

    @Override
    public void delete(T object) {
        try {
            manager.getTransaction().begin();
            manager.remove(object);
            manager.getTransaction().commit();
            System.out.println(object.getClass().getSimpleName() + " deleted successfully.");
        }
        catch (IllegalArgumentException iae) {
            System.out.println(object.getClass().getSimpleName() + " already removed.");
        }
    }
}
