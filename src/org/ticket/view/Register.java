package org.ticket.view;

import org.ticket.model.utils.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register implements ActionListener {
    private JFrame frame;
    private JPanel panel;

    private JLabel lapp = new JLabel("Cadastro");
    private JLabel lversion = new JLabel("Versão: " + Properties.version());

    private JLabel lname = new JLabel("Nome");
    private JLabel lnickname = new JLabel("Usuário");
    private JLabel lpassword = new JLabel("Senha");
    private JLabel lusertype = new JLabel("Tipo de usuário");

    private JTextField tfname = new JTextField();
    private JTextField tfnickname = new JTextField();
    private JPasswordField tfpassword = new JPasswordField();

    private JComboBox cbtype;

    private JButton bback = new JButton("Voltar ao menu");
    private JButton bregister = new JButton("Cadastrar");

    public Register() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Cadastro ao sistema");
        frame.setResizable(false);
        frame.setSize(600, 400);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lapp.setBounds(50, 50, 150, 30);
        lapp.setFont(new Font("Arial", Font.ITALIC, 22));
        panel.add(lapp);

        lname.setBounds(50, 100, 150, 30);
        panel.add(lname);

        lnickname.setBounds(50, 150, 150, 30);
        panel.add(lnickname);

        lpassword.setBounds(50, 200, 150, 30);
        panel.add(lpassword);

        lusertype.setBounds(50, 250, 150, 30);
        panel.add(lusertype);

        tfname.setBounds(200, 100, 200, 30);
        panel.add(tfname);

        tfnickname.setBounds(200, 150, 200, 30);
        panel.add(tfnickname);

        tfpassword.setBounds(200, 200, 200, 30);
        panel.add(tfpassword);

        String types[] = {"Usuário", "Gerente"};
        cbtype = new JComboBox(types);

        cbtype.setBounds(200, 250, 150, 30);
        panel.add(cbtype);

        bback.setBounds(100, 300, 100, 30);
        panel.add(bback);

        bregister.setBounds(200, 300, 100, 30);
        panel.add(bregister);

        lversion.setBounds(530, 340, 150, 30);
        lversion.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(lversion);

        bback.addActionListener(this);
        bregister.addActionListener(this);

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
        }

        if (source.equals(bregister)) {
            // TODO: Call RegisterController to save the user
        }
    }
}
