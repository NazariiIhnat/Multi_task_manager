package task.managment.bossmode;

import components.custom.AbstractTaskTable;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@Component
class BossModeTaskTable extends AbstractTaskTable {

    private final String[] headerNames = {"\u2116", "ID", "Title","Status"};
    private final Class[] columnTypes = {Long.class, Long.class, String.class, Boolean.class};
    private final boolean[] columnEditable = {false, false, false, true};
    private JScrollPane scrollPane;

     BossModeTaskTable() {
        super();
        initTaskTable();
    }

    private void initTaskTable() {
        setModel(new DefaultTableModel(new Object[][] {}, headerNames) {
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            public boolean isCellEditable(int row, int column) {
                return columnEditable[column];
            }
        });

        TableColumnModel cm = getColumnModel();
        cm.getColumn(0).setPreferredWidth(35);
        cm.getColumn(0).setMinWidth(35);
        cm.getColumn(0).setMaxWidth(44);
        cm.getColumn(1).setPreferredWidth(35);
        cm.getColumn(1).setMinWidth(35);
        cm.getColumn(1).setMaxWidth(44);
        cm.getColumn(2).setPreferredWidth(255);
        cm.getColumn(2).setMinWidth(30);
        cm.getColumn(2).setMaxWidth(380);
        cm.getColumn(3).setPreferredWidth(80);
        cm.getColumn(3).setMinWidth(44);
        cm.getColumn(3).setMaxWidth(120);
        getModel().addTableModelListener(getCheckBoxModeListener(3));
        JPopupMenu popupMenu = new JPopupMenu();
        setPopupMenu(popupMenu);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
    }

     JScrollPane getScrollPane() {
        return scrollPane;
    }
}
