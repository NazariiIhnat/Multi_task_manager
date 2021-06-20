package work.boss_mode;

import components.custom.AbstractTaskTable;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@Component
public class BossModeTaskTable extends AbstractTaskTable {

    private final String[] headerNames = {"\u2116", "ID", "Title","Status", "Description"};
    private final Class[] columnTypes = {Long.class, Long.class, String.class, Boolean.class, String.class};
    private final boolean[] columnEditable = {false, false, false, true, false};
    private JScrollPane scrollPane;

    public BossModeTaskTable() {
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
        cm.getColumn(0).setPreferredWidth(30);
        cm.getColumn(0).setMinWidth(30);
        cm.getColumn(0).setMaxWidth(39);
        cm.getColumn(1).setPreferredWidth(30);
        cm.getColumn(1).setMinWidth(30);
        cm.getColumn(1).setMaxWidth(39);
        cm.getColumn(2).setPreferredWidth(280);
        cm.getColumn(2).setMinWidth(30);
        cm.getColumn(2).setMaxWidth(380);
        cm.getColumn(3).setMinWidth(41);
        cm.getColumn(3).setMaxWidth(41);
        cm.getColumn(3).setPreferredWidth(41);

        //description column is invisible.
        cm.getColumn(4).setMinWidth(0);
        cm.getColumn(4).setMaxWidth(0);
        cm.getColumn(4).setPreferredWidth(0);
        getModel().addTableModelListener(getCheckBoxModeListener(3));

        JPopupMenu popupMenu = new JPopupMenu();
        setPopupMenu(popupMenu);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
