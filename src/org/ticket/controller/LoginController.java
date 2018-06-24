package org.ticket.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.ticket.model.User;
import org.ticket.model.utils.Tuple;

import javax.persistence.PersistenceException;
import java.util.Optional;

public class LoginController {

    private static UserController user = new UserController();

    public LoginController() {

    }

    public static Tuple<String, Boolean> authenticate(String nick, char password[]) {

        Optional<User> u;
        Tuple<String, Boolean> response;

        try {
            u = user.search("nick", nick);
        }  catch(PersistenceException ex) {
            response = new Tuple<>("Usuário não encontrado", false);
            return response;
        }

        // FIXME: Don't do this, really! Operate with the array available as parameter
        HashFunction sha256 = Hashing.sha256();
        String pass = sha256.newHasher()
                .putString(String.valueOf(password), Charsets.UTF_8)
                .putString(u.get().getName(), Charsets.UTF_8)
                .hash()
                .toString();


        // User authenticated
        if (!u.get().getPassword().equals(pass)) {
            response = new Tuple<>("Senha incorreta", false);
            return response;
        }

        response = new Tuple<>("Usuário autenticado", true);
        return response;
    }
}
