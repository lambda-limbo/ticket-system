package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.dao.GenericDAO;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class TicketController {
    private GenericDAO<Ticket> ticketDAO;

    public TicketController() {
        ticketDAO = new GenericDAO<>();
    }

    public void save(Ticket t) throws PersistenceException {
        try {
            this.search(t.getTitle());
        }
        catch (NoResultException ex) {
            ticketDAO.save(t);
        }
    }

    public void delete(Ticket t) throws PersistenceException  {
        ticketDAO.delete(t);
    }

    public Ticket search(int id) throws PersistenceException {
        return ticketDAO.search(Ticket.class, "id", id);
    }

    public Ticket search(String title) throws PersistenceException  {
        return ticketDAO.search(Ticket.class, "title", title);
    }

    public List<Ticket> list() throws PersistenceException  {
        return ticketDAO.search(Ticket.class);
    }
}
