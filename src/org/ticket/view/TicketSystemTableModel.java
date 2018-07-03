package org.ticket.view;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TicketSystemTableModel extends AbstractTableModel {

    private final String columns[] = {"Identificador", "Título", "Prioridade", "Autor", "Resolvido"};
    private Vector<Object[]> data = new Vector<>();

    public void push(Object data[]) {
        this.data.add(data);
        fireTableRowsInserted(this.data.size(), this.data.size()+1);
    }

    public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    @Override
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

    public Object[] getValue(int row) {
        return data.get(row);
    }
}
