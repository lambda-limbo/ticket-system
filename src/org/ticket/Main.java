package org.ticket;

import org.ticket.controller.UserController;
import org.ticket.model.User;
import org.ticket.model.utils.Logger;
import org.ticket.model.utils.Properties;
import org.ticket.view.Greetings;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            Logger.set(Logger.LOG_LEVEL.NORMAL);
        } else {
            if (args[0].equals("--debug")) {
                Logger.set(Logger.LOG_LEVEL.DEBUG);

                User u = new User("Administrador do sistema", "admin", "entrophy",
                        User.UserType.manager);

                UserController uc = new UserController();
                uc.save(u);
            }
        }

        Logger.log("Ticket System Version: " + Properties.version(), Logger.LOG_LEVEL.NORMAL);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Open the main window and assure everything is set before showing the GUI.
        SwingUtilities.invokeLater(new Greetings());
    }
}