package org.ticket.view;

import org.ticket.model.utils.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Greetings implements ActionListener, Runnable {

    private JFrame frame;
    private JPanel panel;

    private JLabel llogin = new JLabel("Login");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());
    private JLabel lmessage = new JLabel("Fazendo login...");

    private JButton blogin = new JButton("Login");
    private JButton bexit = new JButton("Sair");

    private JLabel lnickname = new JLabel("Usuário");
    private JLabel lpassword = new JLabel("Senha");

    private JTextField tfnickname = new JTextField();
    private JPasswordField tfpassword = new JPasswordField();

    public Greetings() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Bem vindo ao Ticket System");
        frame.setResizable(false);
        frame.setSize(340, 400);
        // I will manually set the position of the elements
        panel.setLayout(null);

        llogin.setBounds(30, 35, 250, 40);
        llogin.setFont(Fonts.big);
        panel.add(llogin);

        lversion.setBounds(210, 330, 150, 30);
        lversion.setFont(Fonts.small);
        panel.add(lversion);

        lnickname.setBounds(30, 100, 100, 30);
        panel.add(lnickname);

        lpassword.setBounds(30, 150, 100, 30);
        panel.add(lpassword);

        tfnickname.setBounds(80, 100, 220, 35);
        panel.add(tfnickname);

        tfpassword.setBounds(80, 150, 220, 35);
        panel.add(tfpassword);

        blogin.setBounds(220, 200, 80, 30);
        panel.add(blogin);

        bexit.setBounds(130, 200, 80, 30);
        panel.add(bexit);

        lmessage.setBounds(290, 260, 220, 30);
        lmessage.setVisible(false);
        panel.add(lmessage);

        blogin.addActionListener(this);
        bexit.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(blogin)) {
            blogin.setEnabled(false);
            tfnickname.setEnabled(false);
            tfpassword.setEnabled(false);

            llogin.setVisible(true);
            // TODO: Call LoginController to login the user
        } else if (e.getSource().equals(bexit)) {
            frame.dispose();
        }
    }

    public void run() {
    }
}
