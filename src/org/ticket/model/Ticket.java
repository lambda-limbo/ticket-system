package org.ticket.model;

import org.hibernate.annotations.Entity;

import javax.persistence.*;

@Entity
@Table(name="TICKET")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name="TICKET_ID")
    private long ticketId;
    @Column(name="TICKET_TITLE")
    private String title;
    @Column(name="TICKET_CONTENT")
    private String content;

    @OneToOne
    private User issuer;

    enum TicketPriority {
        LOW,
        MEDIUM,
        HIGH
    }

    @Column(name="TICKET_PRIORITY")
    private TicketPriority ticketPriority;

    Ticket(String title, String content, TicketPriority ticketPriority, User issuer) {
        this.title = title;
        this.content = content;
        this.ticketPriority  = ticketPriority;
        this.issuer = issuer;
    }
}
