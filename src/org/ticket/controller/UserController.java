package org.ticket.controller;

import org.ticket.model.User;
import org.ticket.model.dao.GenericDAO;

import java.util.List;

public class UserController {
    GenericDAO<User> userDAO;

    public UserController() {
        userDAO = new GenericDAO<>();
    }

    public void save(User u) {
        // Check if there's a User with the same nickname.
        if(userDAO.search(User.class, "nick", u.getNick()) == null) {
            userDAO.save(u);
        }
    }

    public void delete(User u) {
        userDAO.delete(u);
    }

    public User search(int id) {
        return userDAO.search(User.class, "id", id);
    }

    public User searchName(String name) {
        return userDAO.search(User.class, "name", name);
    }

    public User searchNickname(String nickname) {
        return userDAO.search(User.class, "nick", nickname);
    }

    public List<User> list() {
        return userDAO.search(User.class);
    }

}
