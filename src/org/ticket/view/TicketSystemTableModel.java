package org.ticket.view;

import org.ticket.model.Ticket;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class TicketSystemTableModel extends AbstractTableModel {

    private final String columns[] = {"Identificador", "Título", "Prioridade", "Autor", "Resolvido"};
    private Vector<Object[]> data;

    public void push(Object data[]) {
        this.data.add(data);
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {
        return data.get(row)[column];
    }
}
