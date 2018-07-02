package org.ticket.view;

import org.ticket.controller.TicketController;
import org.ticket.model.Ticket;
import org.ticket.model.utils.Session;
import org.ticket.model.utils.Tuple;

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

    private JComboBox<String> cbpriority;

    private JTextField tfproblem = new JTextField();

    private JButton bcreate = new JButton("Criar Chamado");

    String types[] = {"Baixa", "Média", "Alta"};

    public CreateTicket() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setTitle("Novo Chamado - Ticket System");
        frame.setResizable(false);
        frame.setSize(700, 500);
        // I will manually set the position of the elements
        panel.setLayout(null);

        ltitle.setBounds(30, 20, 40, 30);
        panel.add(ltitle);

        tftitle.setBounds(90, 20, 580, 30);
        panel.add(tftitle);

        lpriority.setBounds(480, 80, 80, 30);
        panel.add(lpriority);

        cbpriority = new JComboBox(types);

        cbpriority.setBounds(550, 80, 120, 30);
        cbpriority.setSelectedIndex(0);
        panel.add(cbpriority);

        ldescription.setBounds(30, 110, 640, 30);
        panel.add(ldescription);

        tfproblem.setBounds(30, 140, 640, 280);
        panel.add(tfproblem);

        bcreate.setBounds(550, 428, 120, 30);
        panel.add(bcreate);

        bcreate.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source.equals(bcreate)) {
            disable();

            Ticket.TicketPriority tp = Ticket.TicketPriority.LOW;

            switch (cbpriority.getSelectedIndex()) {
                case 0:
                    tp = Ticket.TicketPriority.LOW;
                    break;
                case 1:
                    tp = Ticket.TicketPriority.MEDIUM;
                    break;
                case 2:
                    tp = Ticket.TicketPriority.HIGH;
                    break;
            }

            TicketController tc = new TicketController();

            Ticket t = new Ticket(tftitle.getText(), tfproblem.getText(), false, tp, Session.user);

            Tuple<String, Boolean> resp = tc.save(t);

            if(!resp.second) {
                JOptionPane.showMessageDialog(null, resp.first, "Erro ao criar ticket",
                        JOptionPane.ERROR_MESSAGE);
                tftitle.grabFocus();
                tftitle.selectAll();
            } else {
                frame.dispose();
                JOptionPane.showMessageDialog(null, resp.first, "Sucesso ao criar ticket",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            enable();
        }
    }

    private void disable() {
        bcreate.setEnabled(false);
    }

    private void enable() {
        bcreate.setEnabled(true);
    }
}
