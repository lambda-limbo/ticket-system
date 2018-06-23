package org.ticket.model.dao;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T> {
    void save(T object);
    T search(Class objClass, String fieldName, String argument) throws PersistenceException;
    T search(Class objClass, String fieldName, int argument) throws PersistenceException ;
    List<T> search(Class objClass) throws PersistenceException ;
    void delete(T object) throws PersistenceException ;
}
