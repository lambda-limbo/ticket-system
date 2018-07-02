package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.dao.GenericDAO;
import org.ticket.model.utils.Tuple;

import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

public class TicketController {

    private GenericDAO<Ticket> ticketDAO;
    private static TicketController instance;

    private TicketController() {
        ticketDAO = new GenericDAO<>();
    }

    public static synchronized TicketController instance() {
        if (instance == null) {
            instance = new TicketController();
        }

        return instance;
    }


    public Tuple<String, Boolean> save(Ticket t) throws PersistenceException {
        Tuple<String, Boolean> resp = new Tuple<>("Ticket Cadastrado!", true);

        // Prevents storing the same object twice.
        try {
            search(t.getTitle()).isPresent();

            resp = new Tuple<>("Ticket com mesmo título encontrado, escolha outro por favor.", false);
        }
        catch (PersistenceException ex) {
            ticketDAO.save(t);
        }

        return resp;
    }

    public void delete(Ticket t) throws PersistenceException  {
        ticketDAO.delete(t);
    }

    public Optional<Ticket> search(int id) throws PersistenceException {
        Optional<Ticket> ticket = Optional.ofNullable(ticketDAO.search(Ticket.class, "id", id));

        return ticket;
    }

    public Optional<Ticket> search(String title) throws PersistenceException  {
        Optional<Ticket> ticket =  Optional.ofNullable(ticketDAO.search(Ticket.class, "title", title));

        return ticket;
    }

    public Optional<List<Ticket>> list() throws PersistenceException  {
        Optional<List<Ticket>> tickets = Optional.ofNullable(ticketDAO.search(Ticket.class));

        return tickets;
    }

    /**
     * @brief Get the last <i>limit</i> itens from the Tickets
     * @param limit The quantity of last items that are retrieved from the list.
     * @return A list containing the last <i>limit</i> items.
     * @throws PersistenceException If not element is found, probably.
     */
    public List<Ticket> list(int limit) throws PersistenceException {
        Optional<List<Ticket>> tickets = Optional.ofNullable(ticketDAO.search(Ticket.class));

        List<Ticket> list = new Vector<>();

        // verify if the size of the list retrieved is greater than the limit itself
        if (tickets.isPresent()) {
            if (tickets.get().size() - limit <= 0) {
                // return the list of tickets
                list = tickets.get();
            } else {
                for (int i = tickets.get().size() - limit; i < tickets.get().size(); i++) {
                    list.add(tickets.get().get(i));
                }
            }
        }

        return list;
    }
}
