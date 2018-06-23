package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.User;
import org.ticket.model.dao.GenericDAO;

import javax.persistence.NoResultException;
import java.util.NoSuchElementException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class UserController {
    private GenericDAO<User> userDAO;

    public UserController() {
        userDAO = new GenericDAO<>();
    }

    public void save(User u) throws PersistenceException  {
        // Prevents storing the same object twice.
        try {
            this.searchNickname(u.getNick()).get();
        }
        catch (NoSuchElementException ex) {
            userDAO.save(u);
        }
    }

    public void delete(User u) throws PersistenceException  {
        try {
            userDAO.delete(u);
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public Optional<User> search(int id) throws PersistenceException {
        Optional<User> user = Optional.empty();

        try {
            user = Optional.ofNullable(userDAO.search(User.class, "id", id));
        }
        catch (NoResultException | NonUniqueResultException ex) {
            throw ex;
        }
        finally {
            return user;
        }
    }

    public Optional<User> searchName(String name) throws PersistenceException {
        Optional<User> user = Optional.empty();

        try {
            user = Optional.ofNullable(userDAO.search(User.class, "name", name));
        }
        catch (NoResultException | NonUniqueResultException ex) {
            throw ex;
        }
        finally {
            return user;
        }
    }

    public Optional<User> searchNickname(String nickname) throws PersistenceException  {
        Optional<User> user = Optional.empty();

        try {
            user = Optional.ofNullable(userDAO.search(User.class, "nick", nickname));
        }
        catch (NoResultException | NonUniqueResultException ex) {
            throw ex;
        }
        finally {
            return user;
        }
    }

    public Optional<List<User>> list() throws PersistenceException  {
        Optional<List<User>> users = Optional.empty();

        try {
            users = Optional.ofNullable(userDAO.search(User.class));
        }
        catch (NoResultException | NonUniqueResultException ex) {
            throw ex;
        }
        finally {
            return users;
        }
    }

}
