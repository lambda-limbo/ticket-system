package org.ticket.model;


import javax.persistence.*;

@Entity(name = "TB_TICKET")
@Table(name="TB_TICKET")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name="TICKET_ID", nullable = false)
    private long id;

    @Column(name="TICKET_TITLE", nullable = false)
    private String title;
    @Column(name="TICKET_CONTENT", nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "TICKET_ISSUERID", nullable = false)
    private User issuer;

    enum TicketPriority {
        LOW,
        MEDIUM,
        HIGH
    }

    @Column(name="TICKET_PRIORITY")
    private TicketPriority ticketPriority;

    protected Ticket() {}

    public Ticket(String title, String content, TicketPriority ticketPriority, User issuer) {
        this.title = title;
        this.content = content;
        this.ticketPriority  = ticketPriority;
        this.issuer = issuer;
    }

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
}
