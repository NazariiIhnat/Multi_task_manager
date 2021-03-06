package task.managment;

import components.custom.AbstractTaskTable;
import entities.Task;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@Component
public class TaskManageTable extends AbstractTaskTable implements TableModelListener{
    private final String [] headerNames = {"\u2116", "ID", "Title", "Sender", "Status"};
    private final Class[] columnTypes = {
            Long.class,
            Long.class,
            String.class,
            String.class,
            Boolean.class
    };
    private final boolean[] columnEditable = {false, false, false, false, true};
    private JPopupMenu popupMenu;
    private JScrollPane scrollPane;

     TaskManageTable() {
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
        cm.getColumn(4).setPreferredWidth(56);
        cm.getColumn(4).setMinWidth(56);
        cm.getColumn(4).setMaxWidth(56);
        getModel().addTableModelListener(getCheckBoxModeListener(4));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
        popupMenu = new JPopupMenu(){
            public void show(java.awt.Component invoker, int x, int y){
                AbstractTaskTable table = (AbstractTaskTable) invoker;
                Task task = table.getSelectedTask();
                for(MenuElement element : popupMenu.getSubElements()){
                    JMenuItem menuItem = (JMenuItem) element;
                    if(isTaskModifyingMenuItem(menuItem) & !task.getSenderNickname().equals("own task"))
                        menuItem.setEnabled(false);
                    else menuItem.setEnabled(true);
                }
                super.show(invoker, x, y);
            }

            private boolean isTaskModifyingMenuItem(JMenuItem item) {
                return item.getText().equals("Update") || item.getText().equals("Delete");
            }
        };
        setPopupMenu(popupMenu);
    }

    public JScrollPane getTaskTableScrollPane(){
        return this.scrollPane;
    }
}

