package components.custom;

import dao.TaskDAO;
import entities.Task;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class AbstractTaskTable extends JTable {

    private JScrollPane taskTableScrollPane;
    private JPopupMenu popupMenu;

    public AbstractTaskTable() {
        taskTableScrollPane = new JScrollPane();
        taskTableScrollPane.setViewportView(this);
        addMouseListener(enableRowSelectionByClickingRMB());
        addMouseListener(showPopupMenuByClickingRMBOnTableRow());
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
    }


    private MouseListener enableRowSelectionByClickingRMB() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = rowAtPoint(e.getPoint());
                if(r >= 0 && r < getRowCount())
                    setRowSelectionInterval(r, r);
                else
                    clearSelection();

                int rowindex = getSelectedRow();
                if(rowindex < 0)
                    return;
                if (e.isPopupTrigger())
                    e.getComponent();
            }
        };
    }

    private MouseListener showPopupMenuByClickingRMBOnTableRow() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkPopup(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                checkPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                checkPopup(e);
            }
        };
    }

    private void checkPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    protected void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public void addPopupMenuItem(JMenuItem menuItem) {
        if(this.popupMenu != null)
            popupMenu.add(menuItem);
    }

    public Task getSelectedTask() {
        int selectedRow = this.getSelectedRow();
        long selectedTaskID = (long) this.getValueAt(selectedRow, 1);
        return TaskDAO.getTaskByID(selectedTaskID);
    }

    public JScrollPane getTaskTableScrollPane() {
        return taskTableScrollPane;
    }

    protected TableModelListener getCheckBoxModeListener(int checkBoxColumn) {
        return new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                int row = e.getFirstRow();
                int column = e.getColumn();
                int idColumn = 1;
                if (column == checkBoxColumn) {
                    TableModel model = (TableModel) e.getSource();
                    Boolean checked = (Boolean) model.getValueAt(row, column);
                    Task task;
                    if (checked) {
                        task = TaskDAO.getTaskByID((Long) model.getValueAt(row, idColumn));
                        task.setDone(true);
                        TaskDAO.saveOrUpdate(task);
                    } else {
                        task = TaskDAO.getTaskByID((Long) model.getValueAt(row, idColumn));
                        task.setDone(false);
                        TaskDAO.saveOrUpdate(task);
                    }
                }
            }
        };
    }
}
