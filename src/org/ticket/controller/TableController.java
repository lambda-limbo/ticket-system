package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.view.TicketSystemTableModel;

import javax.persistence.PersistenceException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public final class TableController {

    private static TicketController tc = new TicketController();

    private TableController() {}

    /**
     * @brief Fills the table model provided.
     * @param tst The table model to be filled.
     */
    public static void fill(TicketSystemTableModel tst) {

        Optional<List<Ticket>> tickets = tc.list();

        for (Ticket ticket : tickets.get()) {
            Object o[] = new Object[5];

            set(ticket, o);
            tst.push(o);
        }
    }

    /**
     * @brief Filter the tickets to
     * @param tst
     * @param id
     */
    public static void filter(TicketSystemTableModel tst, Integer id) {

        try {
            Ticket ticket = tc.search(id).get();
            clear(tst);

            Object o[] = new Object[5];

            set(ticket, o);
            tst.push(o);

        } catch(PersistenceException ex) {
            // No ticket found remove Filter (This is the default operation)
            clear(tst);
            fill(tst);
        }

    }

    /**
     * @brief Clear the data vector from which the table uses as the data provider
     * @param tst
     */
    private static void clear(TicketSystemTableModel tst) {

        int upper = tst.getRowCount();

        for (int i = upper-1; i >= 0; i--) {
            tst.remove(i);
        }
    }

    private static Object[] set(Ticket ticket, Object o[]) {
        o[0] = "#" + ticket.getTicketId();
        o[1] = ticket.getTitle();

        switch (ticket.getTicketPriority()) {
            case 0:
                o[2] = "Baixa";
                break;
            case 1:
                o[2] = "Média";
                break;
            case 2:
                o[2] = "Alta";
                break;
        }

        o[3] = ticket.getIssuer().getName();

        if (ticket.getSolved()) {
            o[4] = "Sim";
        } else {
            o[4] = "Não";
        }

        return o;
    }
}
