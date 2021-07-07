package task.managment;

import components.custom.TaskUpdateFrame;
import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import message.ShowMessage;
import javax.swing.*;
import java.awt.event.*;

@Component("employeeTaskUpdater")
public class TaskUpdater {

    private WorkFrame workFrame;
    private TableRefresher tableRefresher;
    private TaskUpdateFrame taskUpdateFrame;
    private Task taskBeforeModifying = null;
    private Task taskAfterModifying = null;

    @Autowired
    public TaskUpdater(WorkFrame workFrame, TableRefresher tableRefresher, TaskUpdateFrame taskUpdateFrame) {
        this.workFrame = workFrame;
        this.tableRefresher = tableRefresher;
        this.taskUpdateFrame = taskUpdateFrame;
        initTaskUpdatePopupMenuItem();
        taskUpdateFrame.getSaveButton().addActionListener(updateTask());
    }

    private void initTaskUpdatePopupMenuItem() {
        JMenuItem menuItem = new JMenuItem("Update");
        menuItem.addActionListener(loadTask());
        workFrame.getTaskManageTable().addPopupMenuItem(menuItem);
    }

    private ActionListener loadTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workFrame.setEnabled(false);
                taskBeforeModifying = workFrame.getTaskManageTable().getSelectedTask();
                taskUpdateFrame.loadTaskToUpdateFrame(taskBeforeModifying);
                taskUpdateFrame.setVisible(true);
            }
        };
    }

    private ActionListener updateTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskUpdateFrame.getTitleTextField().isEmpty())
                    ShowMessage.error("Title can not be empty.");
                else if (taskUpdateFrame.getDescriptionTextArea().isEmpty())
                    ShowMessage.error("Description can not be empty.");
                else {
                    readChanges();
                    TaskDAO.saveOrUpdate(taskAfterModifying);
                    taskUpdateFrame.dispose();
                    tableRefresher.refreshTable();
                    workFrame.setEnabled(true);
                    workFrame.toFront();
                }
            }
        };
    }

    private void readChanges() {
        taskAfterModifying = workFrame.getTaskManageTable().getSelectedTask();
        taskAfterModifying.setTitle(taskUpdateFrame.getTitleTextField().getText());
        taskAfterModifying.setDescription(taskUpdateFrame.getDescriptionTextArea().getText());
        taskAfterModifying.setDate(taskUpdateFrame.getCalendarPanel().getSelectedDate());
    }

    TaskUpdateFrame getTaskUpdateFrame() {
        return taskUpdateFrame;
    }
}
