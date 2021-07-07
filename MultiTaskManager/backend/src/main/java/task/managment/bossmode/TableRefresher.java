package task.managment.bossmode;

import dao.TaskDAO;
import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.WorkFrame;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

@Component("bossModeTableRefresher")
public class TableRefresher {

    private BossModeFrame bossModeFrame;
    private List<Task> selectedUserTasks = new ArrayList<>();

    @Autowired
    public TableRefresher(BossModeFrame bossModeFrame) {
        this.bossModeFrame = bossModeFrame;
    }

    public void refreshSelectedUsersTaskTable(User receiver){
        DefaultTableModel tableModel = (DefaultTableModel) bossModeFrame.getBossModeTaskTable().getModel();
        nullifyTasksTable(tableModel);
        selectedUserTasks = loadSelectedUserTasks(receiver);
        int counter = 1;
        for(Task task : selectedUserTasks){
            tableModel.addRow(new Object[]{counter, task.getId(), task.getTitle(), task.isDone(), task.getDescription()});
            counter += 1;
        }
    }

    private void nullifyTasksTable(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        for(int i = rowCount - 1; i >= 0; i--)
            tableModel.removeRow(i);
    }

    private List<Task> loadSelectedUserTasks(User user) {
        String sender = WorkFrame.getLoggedInUser().getNickname();
        String receiver = user.getNickname();
        return TaskDAO.getUsersTasksBySenderNickname(receiver, sender);
    }

    public List<Task> getSelectedUserTasks() {
        return selectedUserTasks;
    }
}
