package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.utils.Tuple;
import org.ticket.view.TicketSystemTableModel;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public final class TableController {

    private static TicketController tc = TicketController.instance();

    private TableController() {}

    /**
     * @brief Fills the table model provided.
     * @param tst The table model to be filled.
     */
    public static void fill(TicketSystemTableModel tst) {

        Optional<List<Ticket>> tickets = tc.list();

        clear(tst);

        if (tickets.isPresent()) {
            for (Ticket ticket : tickets.get()) {
                Object o[] = new Object[5];

                set(ticket, o);
                tst.push(o);
            }
        }
    }

    /**
     * @brief Filter the tickets available in the database
     * @param tst
     * @param id
     */
    public static Tuple<String, Boolean> filter(TicketSystemTableModel tst, Integer id) {

        Tuple<String, Boolean> resp = new Tuple<>("", true);

        try {
            Ticket ticket = tc.search(id).get();
            clear(tst);

            Object o[] = new Object[5];

            set(ticket, o);
            tst.push(o);

        } catch(PersistenceException ex) {
            // No ticket found remove Filter (This is the default operation)
            resp.first = "Nenhum chamado encontrado com o código " + id + ".";
            resp.second = false;
            return resp;
        }

        return resp;
    }

    /**
     *
     * @param tst
     * @param author
     * @return
     */
    public static Tuple<String, Boolean> filter(TicketSystemTableModel tst, String author) {

        Tuple<String, Boolean> resp = new Tuple<>("", true);

        try {
            List<Ticket> tickets = tc.list(author);
            clear(tst);

            for (Ticket t : tickets) {
                Object o[] = new Object[5];

                set(t, o);
                tst.push(o);
            }

        } catch(PersistenceException ex) {
            // No ticket found remove Filter (This is the default operation)
            resp.first = "Nenhum chamado encontrado com o nome " + author + ".";
            resp.second = false;
            return resp;
        }

        return resp;
    }

    /**
     *
     * @param tst
     * @param tp
     * @return
     */
    public static Tuple<String, Boolean> filter(TicketSystemTableModel tst, Ticket.TicketPriority tp) {

        Tuple<String, Boolean> resp = new Tuple<>("", true);

        try {
            List<Ticket> tickets = tc.list(tp);
            clear(tst);

            for (Ticket t : tickets) {
                Object o[] = new Object[5];

                set(t, o);
                tst.push(o);
            }

        } catch(PersistenceException ex) {
            // No ticket found remove Filter (This is the default operation)
            resp.first = "Nenhum chamado encontrado com a prioridade pesquisada.";
            resp.second = false;
            return resp;
        }

        return resp;
    }

    public static Ticket getSelectedTicket(TicketSystemTableModel tst, int index) {

        Optional<Ticket> ticket = null;
        Object result[] = tst.getValue(index);

        try {
            String formattedId = (String)result[0];

            int id = Integer.valueOf(formattedId.substring(1));

            ticket = TicketController.instance().search(id);
        } catch(PersistenceException ex) {
            // This branch is less likely to happen, really. It is already on the table.
        }

        return ticket.get();
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

    /**
     *
     * @param ticket
     * @param o
     * @return
     */
    private static Object[] set(Ticket ticket, Object o[]) {
        o[0] = "#" + ticket.getId();
        o[1] = ticket.getTitle();
        o[2] = ticket.getFormattedPriority(ticket.getPriority());
        o[3] = ticket.getIssuer().getName();

        if (ticket.getSolved() == 1) {
            o[4] = "Sim";
        } else {
            o[4] = "Não";
        }

        return o;
    }
}
