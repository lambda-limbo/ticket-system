package org.ticket.view;

import org.ticket.model.utils.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JLabel lnickname = new JLabel("Usuário");
    private JLabel lpassword = new JLabel("Senha");

    private JLabel lapp = new JLabel("Login");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());

    private JButton bback = new JButton("Voltar ao menu");
    private JButton blogin = new JButton("Cadastrar");

    private JTextField tfnickname = new JTextField();
    private JPasswordField tfpassword = new JPasswordField();

    public Login() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Login ao sistema");
        frame.setResizable(false);
        frame.setSize(600, 400);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lapp.setBounds(50, 30, 150, 30);
        lapp.setFont(new Font("Arial", Font.ITALIC, 22));
        panel.add(lapp);

        lnickname.setBounds(50, 100, 100, 30);
        panel.add(lnickname);

        lpassword.setBounds(50, 150, 100, 30);
        panel.add(lpassword);

        tfnickname.setBounds(100, 100, 300, 30);
        panel.add(tfnickname);

        tfpassword.setBounds(100, 150, 300, 30);
        panel.add(tfpassword);

        bback.setBounds(200, 250, 120, 30);
        panel.add(bback);

        blogin.setBounds(350, 250, 100, 30);
        panel.add(blogin);

        lversion.setBounds(530, 340, 150, 30);
        lversion.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(lversion);

        bback.addActionListener(this);
        blogin.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(bback)) {
            frame.dispose();
            new Greetings();
        } else if (source.equals(blogin)) {
            // TODO: Call RegisterController to save the user
        }
    }
}
