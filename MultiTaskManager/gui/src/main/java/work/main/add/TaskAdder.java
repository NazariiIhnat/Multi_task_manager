package work.main.add;

import components.custom.DatePicker;
import dao.TaskDAO;
import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.InputVerifier;
import utils.ShowMessage;
import utils.TableRefresher;
import work.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

@Component
public class TaskAdder {

    private MainFrame mainFrame;
    private TaskAddFrame taskAddFrame;
    private DatePicker datePicker;

    @Autowired
    public TaskAdder(MainFrame mainFrame, TaskAddFrame taskAddFrame, DatePicker datePicker) {
        this.mainFrame = mainFrame;
        this.taskAddFrame = taskAddFrame;
        this.datePicker = datePicker;
        datePicker.addMenuItem(addTaskMenuItem());
        taskAddFrame.getOkButton().addActionListener(saveTaskAction());
    }

    private JMenuItem addTaskMenuItem() {
        JMenuItem menuItem = new JMenuItem("Add task");
        menuItem.addActionListener(setVisibleAddTaskFrame());
        return menuItem;
    }

    private AbstractAction setVisibleAddTaskFrame() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setEnabled(false);
                taskAddFrame.showFrame();
            }
        };
    }

    private ActionListener saveTaskAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEmptyInput()){
                    Task task = getTaskForSave();
                    TaskDAO.saveOrUpdate(task);
                    TableRefresher.refreshOwnTaskTable();
                    taskAddFrame.setVisible(false);
                    mainFrame.setEnabled(true);
                    mainFrame.toFront();
                }
            }
        };
    }

    private Task getTaskForSave() {
        String title = taskAddFrame.getTitle();
        String description = taskAddFrame.getDescription();
        LocalDate date = datePicker.getSelectedDate();
        User user = MainFrame.getLoggedInUser();
        return new Task(title, description, date, user);
    }

    private boolean isEmptyInput() {
        if(InputVerifier.textComponentIsEmpty(taskAddFrame.getTitleTextField(),
                "Title")) {
            ShowMessage.error("Title can not be empty.");
            return true;
        }
        else if (InputVerifier.textComponentIsEmpty(taskAddFrame.getDescriptionTextArea(),
                "Description")) {
            ShowMessage.error("Description can not be empty");
            return true;
        }
        else {
            return false;
        }
    }
}
