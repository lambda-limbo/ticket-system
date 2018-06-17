package org.ticket.controller;

import org.ticket.model.Ticket;
import org.ticket.model.dao.GenericDAO;

import java.util.List;

public class TicketController {
    GenericDAO<Ticket> ticketDAO;

    public TicketController() {
        ticketDAO = new GenericDAO<>();
    }

    public void save(Ticket t) {
        if(search(t.getTitle()) == null) {
            ticketDAO.save(t);
        }
    }

    public void delete(Ticket t) {
        ticketDAO.delete(t);
    }

    public Ticket search(int id) {
        return ticketDAO.search(Ticket.class, "id", id);
    }

    public Ticket search(String title) {
        return ticketDAO.search(Ticket.class, "title", title);
    }

    public List<Ticket> list() {
        return ticketDAO.search(Ticket.class);
    }
}
