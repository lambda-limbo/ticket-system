package org.ticket.model.dao;

import java.util.List;

public interface IGenericDAO<T> {
    boolean save(T object);
    T search(Class objClass, String fieldName, String argument);
    T search(Class objClass, String fieldName, int argument);
    List<T> search(Class objClass);
    boolean delete(T object);
}
