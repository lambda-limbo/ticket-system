package org.ticket.view;

import org.ticket.controller.UserController;
import org.ticket.model.User;
import org.ticket.model.User.UserType;
import org.ticket.model.utils.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register implements ActionListener {
    private JFrame frame;
    private JPanel panel;

    private JLabel lapp = new JLabel("Cadastro");

    private JLabel lname = new JLabel("Nome");
    private JLabel lnickname = new JLabel("Usuário");
    private JLabel lpassword = new JLabel("Senha");
    private JLabel lusertype = new JLabel("Tipo de usuário");

    private JTextField tfname = new JTextField();
    private JTextField tfnickname = new JTextField();
    private JPasswordField tfpassword = new JPasswordField();

    private JComboBox cbtype;

    private JButton bback = new JButton("Voltar");
    private JButton bregister = new JButton("Cadastrar");

    public Register() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Cadastro - TicketSystem");
        frame.setResizable(false);
        frame.setSize(350, 400);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lapp.setBounds(30, 35, 150, 30);
        lapp.setFont(Fonts.big);
        panel.add(lapp);

        lname.setBounds(30, 100, 150, 30);
        panel.add(lname);

        lnickname.setBounds(30, 150, 150, 30);
        panel.add(lnickname);

        lpassword.setBounds(30, 200, 150, 30);
        panel.add(lpassword);

        lusertype.setBounds(30, 250, 150, 30);
        panel.add(lusertype);

        tfname.setBounds(100, 100, 200, 30);
        panel.add(tfname);

        tfnickname.setBounds(100, 150, 200, 30);
        panel.add(tfnickname);

        tfpassword.setBounds(100, 200, 200, 30);
        panel.add(tfpassword);

        String types[] = {"Usuário", "Gerente"};
        cbtype = new JComboBox(types);

        cbtype.setBounds(150, 250, 150, 30);
        panel.add(cbtype);

        bback.setBounds(100, 300, 100, 30);
        panel.add(bback);

        bregister.setBounds(200, 300, 100, 30);
        panel.add(bregister);

        bback.addActionListener(this);
        bregister.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(bback)) {
            frame.dispose();
        }

        if (source.equals(bregister)) {
            disable();

            UserType ut = UserType.client;

            switch (cbtype.getSelectedIndex()) {
                case 0:
                    ut = UserType.client;
                    break;
                case 1:
                    ut = UserType.manager;
                    break;
            }

            UserController uc = new UserController();

            // FIXME: This is harmful, don't do String.valueOf(tfpassword.getPassword())) because you are removing
            // FIXME: the purpose of security over here. We have probably to change the codebase of the user.
            User user = new User(tfname.getText(), tfnickname.getText(), String.valueOf(tfpassword.getPassword()), ut);

            Tuple<String, Boolean> resp = uc.save(user);

            if (!resp.second) {
                JOptionPane.showMessageDialog(null, resp.first, "Erro de cadastro",
                        JOptionPane.ERROR_MESSAGE);
                tfnickname.grabFocus();
                tfnickname.selectAll();
            } else {
                JOptionPane.showMessageDialog(null, resp.first, "Sucesso de cadastro",
                        JOptionPane.INFORMATION_MESSAGE);
                tfname.setText("");
                tfnickname.setText("");
                tfpassword.setText("");
                cbtype.setSelectedIndex(0);
            }

            enable();
        }
    }

    private void disable() {
        bregister.setEnabled(false);
        bback.setEnabled(false);
    }

    private void enable() {
        bregister.setEnabled(true);
        bback.setEnabled(true);
    }
}
