package org.ticket.view;

import org.ticket.model.Ticket;
import org.ticket.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketsView implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JTable ttickets = new JTable(new TicketSystemTableModel());

    private JLabel lsearch = new JLabel("Buscar por identificador");

    private JTextField tfsearch = new JTextField();

    private JButton bsearch = new JButton("Buscar");
    private JButton bcreate = new JButton("Novo Chamado");
    private JButton bsolve = new JButton("Resolver Chamado");
    private JButton bopen = new JButton("Abrir Chamado");

    public TicketsView() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Chamados - Ticket System");
        frame.setResizable(false);
        frame.setSize(700, 500);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lsearch.setBounds(30, 20, 150, 30);
        panel.add(lsearch);

        tfsearch.setBounds(30, 45, 550, 30);
        panel.add(tfsearch);

        bsearch.setBounds(590, 45, 90, 30);
        panel.add(bsearch);

        ttickets.setBounds(30, 100, 650, 320);
        panel.add(ttickets);

        bcreate.setBounds(30, 430, 100, 30);
        panel.add(bcreate);

        bopen.setBounds(450, 430, 100, 30);
        panel.add(bopen);

        // TODO: Limit who can close the issue (the own issuer and a manager)
        bsolve.setBounds(560, 430, 120, 30);
        panel.add(bsolve);

        bsearch.addActionListener(this);
        bcreate.addActionListener(this);
        bopen.addActionListener(this);
        bsolve.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bcreate)) {
            new CreateTicket();
        }

        if (e.getSource().equals(bopen)) {
            // TODO: Get the ticket ID from the selected row
            new OpenTicket(new Ticket("Exemplo chamado", "Muito conteudo", Ticket.TicketPriority.HIGH,
                    new User("Rafael Campos Nunes", "rafaelcn", "asdasd")));
        }

        if (e.getSource().equals(bsolve)) {
            // TODO: Remove row from the tickets table.
            // TODO: We should also create some kind of filter to filter information.
        }
    }
}
