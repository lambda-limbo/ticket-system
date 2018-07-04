package org.ticket.view;

import org.ticket.controller.TableController;
import org.ticket.controller.TicketController;
import org.ticket.model.Ticket;
import org.ticket.model.User;
import org.ticket.model.utils.Session;
import org.ticket.model.utils.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TicketsView implements ActionListener, KeyListener {

    private JFrame frame;
    private JPanel panel;

    private static TicketSystemTableModel tst = new TicketSystemTableModel();

    private JTable ttickets;
    private JScrollPane sptable = new JScrollPane(ttickets);

    private JLabel lsearch = new JLabel("Buscar por: ");

    private JTextField tfsearch = new JTextField();

    private JComboBox cbfilter;

    private JButton bsearch = new JButton("Pesquisar");
    private JButton bcreate = new JButton("Novo Chamado");
    private JButton bsolve = new JButton("Resolver Chamado");
    private JButton bopen = new JButton("Abrir Chamado");

    public TicketsView() {
        frame = new JFrame();
        panel = new JPanel();

        ttickets = new JTable(tst);

        frame.setTitle("Chamados - Ticket System");
        frame.setResizable(false);
        frame.setSize(700, 500);
        // I will manually set the position of the elements
        panel.setLayout(null);

        lsearch.setBounds(30, 20, 150, 30);
        panel.add(lsearch);

        tfsearch.setBounds(30, 65, 530, 30);
        panel.add(tfsearch);

        bsearch.setBounds(575, 65, 90, 30);
        panel.add(bsearch);

        String filters[] = {"Por identificador", "Por Autor", "Por Prioridade"};
        cbfilter = new JComboBox(filters);

        cbfilter.setBounds(100, 20, 120, 35);
        panel.add(cbfilter);

        sptable.setBounds(30, 100, 635, 320);
        panel.add(sptable);

        sptable.getViewport().add(ttickets);
        ttickets.setFillsViewportHeight(true);

        bcreate.setBounds(30, 430, 100, 30);
        panel.add(bcreate);

        bopen.setBounds(440, 430, 100, 30);
        panel.add(bopen);

        bsolve.setBounds(550, 430, 120, 30);
        panel.add(bsolve);

        tfsearch.addKeyListener(this);

        bsearch.addActionListener(this);
        bcreate.addActionListener(this);
        bopen.addActionListener(this);
        bsolve.addActionListener(this);

        if (Session.user.getUserType() == User.UserType.client) {
            bsolve.setEnabled(false);
        }

        // Fill the table with some tickets
        try {
            TableController.fill(tst);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, relate ao desenvolvedor a " +
                            "seguinte ensagem: " + ex.getMessage() + ".",
                    "Erro de sistema", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bcreate)) {
            new CreateTicket();
        }

        if (e.getSource().equals(bopen)) {
            // TODO: Get the ticket ID from the selected row
            int row = ttickets.getSelectedRow();

            if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um chamado",
                            "Erro de seleção", JOptionPane.WARNING_MESSAGE);
                } else {
                    Ticket t = TableController.getSelectedTicket(tst, row);
                    new OpenTicket(t);
            }
        }

        if (e.getSource().equals(bsolve)) {
            // TODO: Remove row from the tickets table.
            // TODO: We should also create some kind of filter to filter information (?).
            System.out.println(ttickets.getSelectedRow());

            int row = ttickets.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um chamado",
                        "Erro de seleção", JOptionPane.WARNING_MESSAGE);
            } else {
                Ticket t = TableController.getSelectedTicket(tst, row);
                t.setSolved(1);
                TicketController.instance().solve(t.getId());
                updateTable();
            }
        }

        if (e.getSource().equals(bsearch)) {
            filter(cbfilter.getSelectedIndex());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(tfsearch) && e.getKeyCode() == KeyEvent.VK_ENTER) {
            bsearch.dispatchEvent(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    static void updateTable() {
        TableController.fill(tst);
        TableController.fill(tst);
    }

    private void filter(int index) {
        if (tfsearch.getText().length() == 0) {
            updateTable();
        } else {
            switch (index) {
                case 0:
                    try {
                        Integer n;

                        n = new Integer(tfsearch.getText());
                        Tuple<String, Boolean> resp = TableController.filter(tst, n);

                        if (!resp.second) {
                            JOptionPane.showMessageDialog(null, resp.first, "Chamado não encontrado",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Insira somente números",
                                "Erro de conversão", JOptionPane.ERROR_MESSAGE);
                        tfsearch.setText("");
                        tfsearch.grabFocus();
                        ex.printStackTrace();
                    }
                    break;
                case 1:
                    String author = tfsearch.getText();

                    Tuple<String, Boolean> resp = TableController.filter(tst, author);

                    if (!resp.second) {
                        JOptionPane.showMessageDialog(null, resp.first, "Chamado não encontrado",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 2:
                    Ticket.TicketPriority tp = Ticket.TicketPriority.LOW;
                    String priority = tfsearch.getText();

                    boolean search = true;

                    switch (priority) {
                        case "Baixa":
                            tp = Ticket.TicketPriority.LOW;
                            break;
                        case "Média":
                            tp = Ticket.TicketPriority.MEDIUM;
                            break;
                        case "Alta":
                            tp = Ticket.TicketPriority.HIGH;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "A prioridade " + priority +
                                    "não existe", "Erro de busca", JOptionPane.ERROR_MESSAGE);
                            search = false;
                            tfsearch.setText("");
                            tfsearch.grabFocus();
                            break;
                    }

                    if (search) {
                        resp = TableController.filter(tst, tp);

                        if (!resp.second) {
                            JOptionPane.showMessageDialog(null, resp.first, "Chamado não encontrado",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;
            }
        }
    }
}