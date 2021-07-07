package task.managment.bossmode;

import dao.TaskDAO;
import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import message.ShowMessage;
import task.managment.WorkFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

@Component
public class TaskSender {

    private BossModeFrame bossModeFrame;
    private TaskSendFrame taskSendFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public TaskSender(BossModeFrame bossModeFrame, TaskSendFrame taskSendFrame, TableRefresher tableRefresherr) {
        this.bossModeFrame = bossModeFrame;
        this.taskSendFrame = taskSendFrame;
        this.tableRefresher = tableRefresherr;
        initSendTaskMenuItem();
        taskSendFrame.getSendButton().addActionListener(sendTask());
        bossModeFrame.getEmployeesList().addMouseListener(selectRowByRMB());
    }

    private void initSendTaskMenuItem() {
        JMenuItem sendTaskMenuItem = new JMenuItem("Send task");
        bossModeFrame.getPopupMenu().add(sendTaskMenuItem);
        sendTaskMenuItem.addActionListener(showSendTaskFrame());
    }

    private AbstractAction showSendTaskFrame() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bossModeFrame.setEnabled(false);
                taskSendFrame.showFrame();
            }
        };
    }

    private AbstractAction sendTask() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEmptyTitleAndDescription()) {
                    Task task = getSendTask();
                    TaskDAO.saveOrUpdate(task);
                    tableRefresher.refreshSelectedUsersTaskTable(bossModeFrame.getSelectedUser());
                    taskSendFrame.dispose();
                    bossModeFrame.setEnabled(true);
                    bossModeFrame.toFront();
                }
            }
        };
    }

    private boolean isEmptyTitleAndDescription() {
        if(taskSendFrame.getTitleTextField().isEmpty()){
            ShowMessage.error("Title is empty.");
            return false;
        } else if (taskSendFrame.getDescriptionTextArea().isEmpty()){
            ShowMessage.error("Description is empty.");
            return false;
        } else return true;
    }

    private Task getSendTask() {
        String title = taskSendFrame.getTitle();
        String description = taskSendFrame.getDescriptionTextArea().getText();
        LocalDate date = taskSendFrame.getDatePicker().getSelectedDate();
        User employee = bossModeFrame.getSelectedUser();
        String senderNickName = WorkFrame.getLoggedInUser().getNickname();
        return new Task(title, description, date, employee, senderNickName);
    }

    private MouseAdapter selectRowByRMB() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    JList list = (JList)e.getSource();
                    int row = list.locationToIndex(e.getPoint());
                    list.setSelectedIndex(row);
                }
            }
        };
    }
}
