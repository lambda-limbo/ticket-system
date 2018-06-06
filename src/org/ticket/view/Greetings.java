package org.ticket.view;

import org.ticket.model.utils.Properties;

import javax.swing.*;
import java.awt.*;

public class Greetings {

    private JFrame frame;
    private JPanel panel;

    private JLabel lapp = new JLabel("Ticket System");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());

    private JButton bregister = new JButton("Registrar");
    private JButton blogin = new JButton("Fazer login");

    public Greetings() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Ticket System");
        //frame.setResizable(false);
        frame.setSize(600, 400);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lapp.setBounds(220, 30, 250, 30);
        lapp.setFont(new Font("Arial", Font.ITALIC, 22));
        panel.add(lapp);

        lversion.setBounds(530, 340, 150, 30);
        lversion.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(lversion);

        blogin.setBounds(170, 200, 100, 30);
        panel.add(blogin);

        bregister.setBounds(320, 200, 100, 30);
        panel.add(bregister);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
