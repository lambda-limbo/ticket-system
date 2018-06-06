package org.ticket.view;

import org.ticket.model.utils.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Greetings implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JLabel lapp = new JLabel("Ticket System");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());

    private JButton bregister = new JButton("Registrar");
    private JButton blogin = new JButton("Fazer login");

    public Greetings() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Bem vindo ao Ticket System");
        frame.setResizable(false);
        frame.setSize(600, 400);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lapp.setBounds(195, 30, 250, 40);
        lapp.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 35));
        panel.add(lapp);

        lversion.setBounds(530, 340, 150, 30);
        lversion.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(lversion);

        blogin.setBounds(170, 200, 100, 30);
        panel.add(blogin);

        bregister.setBounds(320, 200, 100, 30);
        panel.add(bregister);

        blogin.addActionListener(this);
        bregister.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(blogin)) {
            frame.dispose();
            new Login();
        }

        if (e.getSource().equals(bregister)) {
            frame.dispose();
            new Register();
        }
    }
}
