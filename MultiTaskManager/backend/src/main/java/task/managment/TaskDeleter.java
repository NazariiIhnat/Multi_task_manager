package task.managment;

import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.TableRefresher;
import task.managment.WorkFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("employeeTaskDeleter")
public class TaskDeleter {

    private WorkFrame workFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public TaskDeleter (WorkFrame workFrame, TableRefresher tableRefresher) {
        this.workFrame = workFrame;
        this.tableRefresher = tableRefresher;
        initDeleteTaskMenuItem();
    }

    private void initDeleteTaskMenuItem() {
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(deleteAction());
        workFrame.getTaskManageTable().addPopupMenuItem(deleteMenuItem);
    }

    private ActionListener deleteAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task taskToDelete = workFrame.getTaskManageTable().getSelectedTask();
                TaskDAO.delete(taskToDelete);
                tableRefresher.refreshTable();
                workFrame.getDescriptionTextArea().clearInputAndSetWatermark();
            }
        };
    }
}
