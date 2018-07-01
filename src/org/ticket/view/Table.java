package org.ticket.view;

import org.ticket.controller.TicketController;
import org.ticket.model.Ticket;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public final class Table {

    private static TicketController tc = new TicketController();

    private Table() {}

    public static void fill(TicketSystemTableModel tst) {
        try {
            Optional<List<Ticket>> tickets = tc.list();

            for (Ticket ticket : tickets.get()) {
                Object o[] = new Object[5];

                o[0] = ticket.getTicketId();
                o[1] = ticket.getTitle();
                o[2] = ticket.getTicketPriority();
                o[3] = ticket.getIssuer();
                o[4] = ticket.getSolved();

                tst.push(o);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, relate ao desenvolvedor.",
                    "Erro de sistema", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
