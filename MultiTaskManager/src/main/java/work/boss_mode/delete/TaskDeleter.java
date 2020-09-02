package work.boss_mode.delete;

import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.TableRefresher;
import work.boss_mode.BossTaskMenageFrame;
import work.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component("bossModeTaskDeleter")
public class TaskDeleter {

    private BossTaskMenageFrame bossTaskMenageFrame;

    @Autowired
    public TaskDeleter(BossTaskMenageFrame bossTaskMenageFrame) {
        this.bossTaskMenageFrame = bossTaskMenageFrame;
        initDeleteTaskMenuItem();
    }

    private void initDeleteTaskMenuItem() {
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(delete());
        bossTaskMenageFrame.getBossModeTaskTable().addPopupMenuItem(deleteMenuItem);
    }

    private ActionListener delete() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task taskToDelete = bossTaskMenageFrame.getBossModeTaskTable().getSelectedTask();
                TaskDAO.delete(taskToDelete);
                TableRefresher.refreshSelectedUsersTaskTable(bossTaskMenageFrame.getSelectedUser());
            }
        };
    }
}
