package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.dao.GenericDAO;
import org.ticket.model.utils.Tuple;

import java.util.NoSuchElementException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class TicketController {
    private GenericDAO<Ticket> ticketDAO;

    public TicketController() {
        ticketDAO = new GenericDAO<>();
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
}
