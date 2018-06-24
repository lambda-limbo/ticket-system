package org.ticket.view;

import org.ticket.controller.LoginController;
import org.ticket.controller.UserController;
import org.ticket.model.utils.Properties;
import org.ticket.model.utils.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Greetings implements ActionListener, KeyListener, Runnable {

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

    // To control access inside the application
    UserController user = new UserController();

    public void run() {}

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

        lmessage.setBounds(210, 260, 220, 30);
        lmessage.setVisible(false);
        panel.add(lmessage);

        tfnickname.addKeyListener(this);
        tfpassword.addKeyListener(this);

        blogin.addActionListener(this);
        bexit.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(blogin)) {
            disable();

            lmessage.setVisible(true);

            Tuple<String, Boolean> resp = LoginController.authenticate(tfnickname.getText(), tfpassword.getPassword());

            if (resp.second) {
                // It is for sure found even calling it without the isPresent() because it was already authenticated.
                new MainWindow(user.search("nick", tfnickname.getText()).get());
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, resp.first, "Erro de Autenticação",
                        JOptionPane.ERROR_MESSAGE);
            }

            enable();
            tfnickname.selectAll();
            lmessage.setVisible(false);

        } else if (e.getSource().equals(bexit)) {
            frame.dispose();
            // Exits soon after the dispose of the frame resources.
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(tfnickname) && e.getKeyCode() == KeyEvent.VK_ENTER) {
            tfpassword.requestFocus();
        }

        if (e.getSource().equals(tfpassword) && e.getKeyCode() == KeyEvent.VK_ENTER) {
            blogin.dispatchEvent(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void disable() {
        blogin.setEnabled(false);
        tfnickname.setEnabled(false);
        tfpassword.setEnabled(false);
    }

    private void enable() {
        blogin.setEnabled(true);
        tfnickname.setEnabled(true);
        tfpassword.setEnabled(true);
    }
}
