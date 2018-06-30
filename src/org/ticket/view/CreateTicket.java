package org.ticket.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTicket implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JLabel ltitle = new JLabel("Título");
    private JLabel lpriority = new JLabel("Prioridade");
    private JLabel ldescription = new JLabel("Descreva o problema no campo abaixo");

    private JTextField tftitle = new JTextField();

    private JComboBox<String> cbpriority = new JComboBox<>();

    public CreateTicket() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Novo Chamado - Ticket System");
        frame.setResizable(false);
        frame.setSize(700, 500);
        // I will manually set the position of the elements
        panel.setLayout(null);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
