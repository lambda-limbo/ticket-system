package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.dao.GenericDAO;

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

    public void save(Ticket t) throws PersistenceException {
        // Prevents storing the same object twice.
        try {
            search(t.getTitle()).isPresent();
        }
        catch (NoSuchElementException ex) {
            ticketDAO.save(t);
        }
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
