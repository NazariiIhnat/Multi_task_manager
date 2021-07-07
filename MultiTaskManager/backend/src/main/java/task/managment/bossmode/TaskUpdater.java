package task.managment.bossmode;

import components.custom.TaskUpdateFrame;
import dao.TaskDAO;
import entities.Task;
import message.ShowMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("sendTaskUpdater")
public class TaskUpdater {

    private BossModeFrame bossModeFrame;
    private TaskUpdateFrame taskUpdateFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public TaskUpdater(BossModeFrame bossModeFrame, TaskUpdateFrame taskUpdateFrame, TableRefresher tableRefresher) {
        this.bossModeFrame = bossModeFrame;
        this.taskUpdateFrame = taskUpdateFrame;
        this.tableRefresher = tableRefresher;
        initTaskUpdatePopupMenuItem();
        taskUpdateFrame.getSaveButton().addActionListener(updateSendTask());
    }

    private void initTaskUpdatePopupMenuItem() {
        JMenuItem updateTaskMenuItem = new JMenuItem("Update");
        updateTaskMenuItem.addActionListener(loadTask());
        bossModeFrame.getBossModeTaskTable().addPopupMenuItem(updateTaskMenuItem);
    }

    private ActionListener loadTask() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bossModeFrame.setEnabled(false);
                taskUpdateFrame.loadTaskToUpdateFrame(bossModeFrame.getBossModeTaskTable().getSelectedTask());
                taskUpdateFrame.setVisible(true);
            }
        };
    }

    private ActionListener updateSendTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taskUpdateFrame.getTitleTextField().getText().isEmpty())
                    ShowMessage.error("Title can not be empty.");
                else if (taskUpdateFrame.getDescriptionTextArea().getText().isEmpty())
                    ShowMessage.error("Description can not be empty.");
                else {
                    Task task = getTaskToSave();
                    TaskDAO.saveOrUpdate(task);
                    tableRefresher.refreshSelectedUsersTaskTable(bossModeFrame.getSelectedUser());
                    closeUpdateFrame();
                    bossModeFrame.setEnabled(true);
                    bossModeFrame.toFront();
                }
            }
        };
    }

    private Task getTaskToSave() {
        Task task = bossModeFrame.getBossModeTaskTable().getSelectedTask();
        task.setTitle(taskUpdateFrame.getTitleTextField().getText());
        task.setDescription(taskUpdateFrame.getDescriptionTextArea().getText());
        task.setDate(taskUpdateFrame.getCalendarPanel().getSelectedDate());
        return task;
    }

    private void closeUpdateFrame() {
        taskUpdateFrame.setVisible(false);
        bossModeFrame.setEnabled(true);
        bossModeFrame.toFront();
    }

    TaskUpdateFrame getTaskUpdateFrame() {
        return taskUpdateFrame;
    }
}
