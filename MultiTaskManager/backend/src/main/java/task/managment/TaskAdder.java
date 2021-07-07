package task.managment;

import dao.TaskDAO;
import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.TableRefresher;
import task.managment.TaskAddFrame;
import task.managment.WorkFrame;
import message.ShowMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

@Component
public class TaskAdder {

    private WorkFrame workFrame;
    private TaskAddFrame taskAddFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public TaskAdder(WorkFrame workFrame, TaskAddFrame taskAddFrame, TableRefresher tableRefresher) {
        this.workFrame = workFrame;
        this.taskAddFrame = taskAddFrame;
        this.tableRefresher = tableRefresher;
        initAddTaskMenuItem();
        taskAddFrame.getOkButton().addActionListener(saveTask());
    }

    private void initAddTaskMenuItem() {
        JMenuItem addTaskMenuItem = new JMenuItem("Add task");
        addTaskMenuItem.addActionListener(showFrame());
        workFrame.getDatePicker().addMenuItem(addTaskMenuItem);
    }

    private AbstractAction showFrame() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workFrame.setEnabled(false);
                taskAddFrame.showFrame();
            }
        };
    }

    private ActionListener saveTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEmptyInput()){
                    Task task = getTaskForSave();
                    TaskDAO.saveOrUpdate(task);
                    tableRefresher.refreshTable();
                    taskAddFrame.setVisible(false);
                    workFrame.setEnabled(true);
                    workFrame.toFront();
                }
            }
        };
    }

    private boolean isEmptyInput() {
        if(taskAddFrame.getTitleTextField().isEmpty()) {
            ShowMessage.error("Title can not be empty.");
            return true;
        }
        else if (taskAddFrame.getDescriptionTextArea().isEmpty()) {
            ShowMessage.error("Description can not be empty");
            return true;
        }
        else {
            return false;
        }
    }

    private Task getTaskForSave() {
        String title = taskAddFrame.getTitleTextField().getText();
        String description = taskAddFrame.getDescriptionTextArea().getText();
        LocalDate date = workFrame.getDatePicker().getSelectedDate();
        User user = WorkFrame.getLoggedInUser();
        return new Task(title, description, date, user);
    }

    public TaskAddFrame getTaskAddFrame() {
        return taskAddFrame;
    }
}
