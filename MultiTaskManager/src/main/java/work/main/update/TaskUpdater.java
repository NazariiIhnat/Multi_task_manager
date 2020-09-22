package work.main.update;

import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ShowMessage;
import utils.TableRefresher;
import work.main.MainFrame;
import work.custom.components.UpdateFrame;

import javax.swing.*;
import java.awt.event.*;

@Component("employeeTaskUpdater")
public class TaskUpdater extends UpdateFrame {

    private MainFrame mainFrame;
    private Task taskBeforeModifying = null;
    private Task taskAfterModifying = null;

    @Autowired
    public TaskUpdater(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initTaskUpdatePopupMenuItem();
        getSaveButton().addActionListener(updateTask());
    }

    private void initTaskUpdatePopupMenuItem() {
        JMenuItem menuItem = new JMenuItem("Update");
        menuItem.addActionListener(loadTask());
        mainFrame.getOwnTaskTable().addPopupMenuItem(menuItem);
    }

    private ActionListener loadTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setEnabled(false);
                taskBeforeModifying = mainFrame.getOwnTaskTable().getSelectedTask();
                loadTaskToUpdateFrame(taskBeforeModifying);
                setVisible(true);
            }
        };
    }

    private ActionListener updateTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getTitleTextField().isEmpty())
                    ShowMessage.error("Title can not be empty.");
                else if (getDescriptionTextArea().isEmpty())
                    ShowMessage.error("Description can not be empty.");
                else {
                    readChanges();
                    TaskDAO.saveOrUpdate(taskAfterModifying);
                    closeAction();
                    //clearFields();
                    TableRefresher.refreshOwnTaskTable();
                }
            }
        };
    }

    private void readChanges() {
        taskAfterModifying = mainFrame.getOwnTaskTable().getSelectedTask();
        taskAfterModifying.setTitle(getTitleTextField().getText());
        taskAfterModifying.setDescription(getDescriptionTextArea().getText());
        taskAfterModifying.setDate(getCalendarPanel().getSelectedDate());
    }

    @Override
    public void closeAction() {
        dispose();
        mainFrame.setEnabled(true);
        mainFrame.toFront();
    }
}
