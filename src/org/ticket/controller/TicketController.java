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
            this.search(t.getTitle()).get();
        }
        catch (NoSuchElementException ex) {
            ticketDAO.save(t);
        }
    }

    public void delete(Ticket t) throws PersistenceException  {
        try {
            ticketDAO.delete(t);
        }
        // t not found
        catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public Optional<Ticket> search(int id) throws PersistenceException {
        Optional<Ticket> ticket = Optional.empty();

        try {
            ticket = Optional.ofNullable(ticketDAO.search(Ticket.class, "id", id));
        }
        catch (NoResultException | NonUniqueResultException ex) {
            throw ex;
        }
        finally {
            return ticket;
        }
    }

    public Optional<Ticket> search(String title) throws PersistenceException  {
        Optional<Ticket> ticket = Optional.empty();

        try {
            ticket = Optional.ofNullable(ticketDAO.search(Ticket.class, "title", title));
        }
        catch (NoResultException | NonUniqueResultException ex) {
            throw ex;
        }
        finally {
            return ticket;
        }
    }

    public Optional<List<Ticket>> list() throws PersistenceException  {
        Optional<List<Ticket>> tickets = Optional.empty();

        try {
            tickets = Optional.ofNullable(ticketDAO.search(Ticket.class));
        }
        catch (NoResultException ex) {
            throw ex;
        }
        finally {
            return tickets;
        }
    }
}
