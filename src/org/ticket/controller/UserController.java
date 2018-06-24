package org.ticket.controller;

import org.ticket.model.User;
import org.ticket.model.dao.GenericDAO;

import java.util.NoSuchElementException;
import javax.persistence.PersistenceException;
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
            search("nick", u.getNick()).isPresent();
        }
        catch (NoSuchElementException ex) {
            userDAO.save(u);
        }
    }

    public void delete(User u) throws PersistenceException  {
        userDAO.delete(u);
    }

    public Optional<User> search(int id) throws PersistenceException {
        Optional<User> user = Optional.ofNullable(userDAO.search(User.class, "id", id));

        return user;
    }

    public Optional<User> search(String fieldname, String value) throws PersistenceException  {
        Optional<User> user = Optional.ofNullable(userDAO.search(User.class, fieldname, value));

        return user;
    }

    public Optional<List<User>> list() throws PersistenceException  {
        Optional<List<User>> users = Optional.ofNullable(userDAO.search(User.class));

        return users;
    }

}
