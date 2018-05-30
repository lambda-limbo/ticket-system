package org.ticket.model;


import javax.persistence.*;

@Entity
@Table(name="TB_TICKET")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name="TICKET_ID")
    private long id;
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

    public long getTicketId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getIssuer() {
        return issuer;
    }

    public TicketPriority getTicketPriority() {
        return ticketPriority;
    }

    Ticket(String title, String content, TicketPriority ticketPriority, User issuer) {
        this.title = title;
        this.content = content;
        this.ticketPriority  = ticketPriority;
        this.issuer = issuer;
    }
}
