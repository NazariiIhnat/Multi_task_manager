package task.managment.bossmode;

import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TaskDeleter {

    private BossModeFrame bossModeFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public TaskDeleter(BossModeFrame bossModeFrame, TableRefresher tableRefresher) {
        this.bossModeFrame = bossModeFrame;
        this.tableRefresher = tableRefresher;
        initDeleteTaskMenuItem();
    }

    private void initDeleteTaskMenuItem() {
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(delete());
        bossModeFrame.getBossModeTaskTable().addPopupMenuItem(deleteMenuItem);
    }

    private ActionListener delete() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task taskToDelete = bossModeFrame.getBossModeTaskTable().getSelectedTask();
                TaskDAO.delete(taskToDelete);
                tableRefresher.refreshSelectedUsersTaskTable(bossModeFrame.getSelectedUser());
            }
        };
    }
}
