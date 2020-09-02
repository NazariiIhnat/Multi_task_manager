package work.boss_mode.update;

import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.TableRefresher;
import work.custom.components.UpdateFrame;
import work.boss_mode.BossTaskMenageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("bossModeTaskUpdater")
public class TaskUpdater extends UpdateFrame {
    private BossTaskMenageFrame bossTaskMenageFrame;

    @Autowired
    public TaskUpdater(BossTaskMenageFrame bossTaskMenageFrame) {
        this.bossTaskMenageFrame = bossTaskMenageFrame;
        initTaskUpdatePopupMenuItem();
        super.getSaveButton().addActionListener(updateSendTask());
    }

    private void initTaskUpdatePopupMenuItem() {
        JMenuItem menuItem = new JMenuItem("Update");
        menuItem.addActionListener(loadTask());
        bossTaskMenageFrame.getBossModeTaskTable().addPopupMenuItem(menuItem);
    }

    private ActionListener loadTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bossTaskMenageFrame.setEnabled(false);
                loadTaskToUpdateFrame(bossTaskMenageFrame.getBossModeTaskTable().getSelectedTask());
                setVisible(true);
            }
        };
    }

    private ActionListener updateSendTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = getTaskToSave();
                TaskDAO.saveOrUpdate(task);
                TableRefresher.refreshSelectedUsersTaskTable(bossTaskMenageFrame.getSelectedUser());
                closeUpdateFrame();
                clearFields();
            }
        };
    }

    private Task getTaskToSave() {
        Task task = bossTaskMenageFrame.getBossModeTaskTable().getSelectedTask();
        task.setTitle(getTitleTextField().getText());
        task.setDescription(getDescriptionTextArea().getText());
        task.setDate(getCalendarPanel().getSelectedDate());
        return task;
    }

    private void closeUpdateFrame() {
        setVisible(false);
        bossTaskMenageFrame.setEnabled(true);
        bossTaskMenageFrame.toFront();
    }

    @Override
    public void closeAction() {
        dispose();
        bossTaskMenageFrame.setEnabled(true);
        bossTaskMenageFrame.toFront();
    }
}
