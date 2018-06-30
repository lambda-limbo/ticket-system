package org.ticket.view;

import org.ticket.model.Ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenTicket implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JLabel lid = new JLabel("ID: ");
    private JLabel ltitle = new JLabel("Título: ");
    private JLabel lauthor = new JLabel("Autor: ");
    private JLabel lpriority = new JLabel("Prioridade: ");

    private JLabel lproblem = new JLabel("Descrição do problema");

    private JTextArea taproblem = new JTextArea();

    private JButton bsolve = new JButton("Resolver");
    private JButton bclose = new JButton("Fechar");


    public OpenTicket(Ticket ticket) {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Chamado #" + ticket.getTicketId() + " - Ticket System");
        frame.setResizable(false);
        frame.setSize(700, 500);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lid.setBounds(30, 20, 220, 30);
        lid.setText(lid.getText() + " " + ticket.getTicketId());
        lid.setFont(Fonts.medium);
        panel.add(lid);

        ltitle.setBounds(30, 40, 250, 30);
        ltitle.setText(ltitle.getText() + " " + ticket.getTitle());
        ltitle.setFont(Fonts.medium);
        panel.add(ltitle);

        lauthor.setBounds(30, 60, 250, 30);
        lauthor.setText(lauthor.getText() + " " + ticket.getIssuer().getName());
        lauthor.setFont(Fonts.medium);
        panel.add(lauthor);

        lpriority.setBounds(30, 80, 250, 30);
        lpriority.setText(lpriority.getText() + " " + ticket.getTicketPriority());
        lpriority.setFont(Fonts.medium);
        panel.add(lpriority);

        lproblem.setBounds(30, 100, 200, 30);
        panel.add(lproblem);

        taproblem.setBounds(30, 130, 640, 290);
        taproblem.setEditable(false);
        panel.add(taproblem);

        bsolve.setBounds(460, 430, 100, 30);
        panel.add(bsolve);

        bclose.setBounds(570, 430, 100, 30);
        panel.add(bclose);

        bsolve.addActionListener(this);
        bclose.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bsolve)) {
            // TODO: Solve the issue, close the window and then get back to the TicketsView
            frame.dispose();
        }

        if (e.getSource().equals(bclose)) {
            frame.dispose();
        }
    }
}
