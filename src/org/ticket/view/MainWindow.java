package org.ticket.view;

import org.ticket.controller.TicketController;
import org.ticket.model.Ticket;
import org.ticket.model.User;
import org.ticket.model.utils.Properties;
import org.ticket.model.utils.Session;

import javax.persistence.PersistenceException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class MainWindow implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JButton bvisualize = new JButton("Controle de chamados");
    private JButton bcontrolUsers = new JButton("Controle de usuários");
    private JButton blogout = new JButton("Logout");

    private JButton bbar = new JButton();

    // Labels related to recent tickets
    private JLabel lrecentTickets = new JLabel("Chamados Recentes");

    private Vector<JLabel> ltickets = new Vector<>(4);

    // Labels related to information of the running machine and user
    private JLabel linformation = new JLabel("Informações");

    private JLabel luser = new JLabel("Usuário: ");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());


    public MainWindow() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Ticket System");
        frame.setResizable(false);
        frame.setSize(700, 500);
        // I will manually set the position of the elements
        panel.setLayout(null);


        bvisualize.setBounds(50, 100, 150, 50);
        panel.add(bvisualize);

        bcontrolUsers.setBounds(50, 200, 150, 50);
        panel.add(bcontrolUsers);

        blogout.setBounds(50, 300, 150, 50);
        panel.add(blogout);

        bbar.setBounds(250, 0, 1, 500);
        bbar.setEnabled(false);
        panel.add(bbar);

        lrecentTickets.setBounds(300, 50, 200, 30);
        lrecentTickets.setFont(Fonts.medium);
        panel.add(lrecentTickets);

        // TODO: Fill tickets information with actual tickets or none if there isn't any.
        TicketController tc = TicketController.instance();

        for (int i = 0;  i < ltickets.capacity(); i++) {
            ltickets.add(i, new JLabel());
        }

        ltickets.get(0).setBounds(300, 100, 400, 30);
        panel.add(ltickets.get(0));

        ltickets.get(1).setBounds(300, 150, 400, 30);
        panel.add(ltickets.get(1));

        ltickets.get(2).setBounds(300, 200, 400, 30);
        panel.add(ltickets.get(2));

        ltickets.get(3).setBounds(300, 250, 400, 30);
        panel.add(ltickets.get(3));

        try {
            List<Ticket> tickets = tc.list(4);

            String text[] = new String[tickets.size()];

            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                text[i] = "#" + ticket.getTicketId() + " Título: " + ticket.getTitle() + " Criado por: " +
                        ticket.getIssuer().getName() + "";
                ltickets.get(i).setText(text[i]);
            }

            if (tickets.size() == 0) {
                ltickets.get(0).setText("Não existem chamados registrados no sistema");
            }
        } catch (PersistenceException ex) {
            ltickets.get(0).setText("Não existem chamados registrados no sistema");
        }

        linformation.setBounds(300, 300, 150, 30);
        linformation.setFont(Fonts.medium);
        panel.add(linformation);

        luser.setBounds(300, 340, 300, 30);
        luser.setText(luser.getText() + Session.user.getName());
        panel.add(luser);

        lversion.setBounds(300, 360, 150, 30);
        panel.add(lversion);

        bvisualize.addActionListener(this);
        bcontrolUsers.addActionListener(this);
        blogout.addActionListener(this);

        if (Session.user.getUserType() == User.UserType.client) {
            bcontrolUsers.setEnabled(false);
        }

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bvisualize)) {
            new TicketsView();
        }

        if (e.getSource().equals(bcontrolUsers)) {
            new Register();
        }

        if (e.getSource().equals(blogout)) {
            frame.dispose();
            new Greetings();
        }
    }
}
