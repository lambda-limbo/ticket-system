package org.ticket.controller;

import org.ticket.model.User;
import org.ticket.model.dao.GenericDAO;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class UserController {
    private GenericDAO<User> userDAO;

    public UserController() {
        userDAO = new GenericDAO<>();
    }

    public void save(User u) throws PersistenceException  {
        // Check if there's a User with the same nickname.
        try {
            this.searchNickname(u.getNick());
        }
        catch (NoResultException ex) {
            userDAO.save(u);
        }
    }

    public void delete(User u) throws PersistenceException  {
        userDAO.delete(u);
    }

    public User search(int id) throws PersistenceException {
        return userDAO.search(User.class, "id", id);
    }

    public User searchName(String name) throws PersistenceException {
        return userDAO.search(User.class, "name", name);
    }

    public User searchNickname(String nickname) throws PersistenceException  {
        return userDAO.search(User.class, "nick", nickname);
    }

    public List<User> list() throws PersistenceException  {
        return userDAO.search(User.class);
    }

}
