package org.ticket.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConn {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private JPAConn() {}

    public synchronized static EntityManager instance() {
        if (entityManager == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");
            entityManager = entityManagerFactory.createEntityManager();
        }

        return entityManager;
    }
}
