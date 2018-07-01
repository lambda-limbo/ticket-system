package org.ticket.controller;

import org.ticket.model.User;
import org.ticket.model.dao.GenericDAO;
import org.ticket.model.utils.Tuple;

import java.util.NoSuchElementException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class UserController {

    private GenericDAO<User> userDAO;

    public UserController() {
        userDAO = new GenericDAO<>();
    }

    public Tuple<String, Boolean> save(User u) {
        Tuple<String, Boolean> resp = new Tuple<>("Usuário Cadastrado!", true);

        // Prevents storing the same object twice.
        try {
            search("nick", u.getNick());

            resp  = new Tuple<>("Nome de usuário ja utilizado, escolha outro por favor.", false);
        } catch (PersistenceException ex) {
            userDAO.save(u);
        }

        return resp;
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
