package org.ticket.view;

import org.ticket.model.User;
import org.ticket.model.utils.Properties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu();

    private JButton bvisualize = new JButton("Controle de chamados");
    private JButton bcontrolUsers = new JButton("Controle de usuários");
    private JButton blogout = new JButton("Logout");

    private JButton bbar = new JButton();

    // Labels related to recent tickets
    private JLabel lrecentTickets = new JLabel("Chamados Recentes");

    private JLabel lticket1 = new JLabel();
    private JLabel lticket2 = new JLabel();
    private JLabel lticket3 = new JLabel();

    // Labels related to information of the running machine and user
    private JLabel linformation = new JLabel("Informações");

    private JLabel luser = new JLabel("Usuário: ");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());


    public MainWindow(User u) {
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
        panel.add(bbar);

        lrecentTickets.setBounds(300, 50, 200, 30);
        lrecentTickets.setFont(Fonts.medium);
        panel.add(lrecentTickets);

        // TODO: Fill tickets information with actual tickets or none if there isn't any.

        linformation.setBounds(300, 300, 150, 30);
        linformation.setFont(Fonts.medium);
        panel.add(linformation);

        luser.setBounds(300, 340, 300, 30);
        luser.setText(luser.getText() + u.getName());
        panel.add(luser);

        lversion.setBounds(300, 360, 150, 30);
        panel.add(lversion);

        bvisualize.addActionListener(this);
        bcontrolUsers.addActionListener(this);
        blogout.addActionListener(this);

        if (u.getUserType() == User.UserType.client) {
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
