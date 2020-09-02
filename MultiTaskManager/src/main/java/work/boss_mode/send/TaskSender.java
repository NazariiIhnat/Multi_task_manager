package work.boss_mode.send;

import dao.TaskDAO;
import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.InputVerifier;
import utils.ShowMessage;
import utils.TableRefresher;
import work.boss_mode.BossTaskMenageFrame;
import work.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

@Component
public class TaskSender {

    private BossTaskMenageFrame bossTaskMenageFrame;
    private SendTaskFrame sendTaskFrame;

    @Autowired
    public TaskSender(BossTaskMenageFrame bossTaskMenageFrame, SendTaskFrame sendTaskFrame) {
        this.bossTaskMenageFrame = bossTaskMenageFrame;
        this.sendTaskFrame = sendTaskFrame;
        addADDTaskMenuItemToEmployeeList();
        sendTaskFrame.setSendButtonListener(sendTaskAction());
    }

    private void addADDTaskMenuItemToEmployeeList() {
        JMenuItem addTaskMenuItem = new JMenuItem("Send task");
        bossTaskMenageFrame.addMenuItemToEmployeeListPopupMenu(addTaskMenuItem);
        addTaskMenuItem.addActionListener(addMenuItemAction());
    }

    private AbstractAction addMenuItemAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bossTaskMenageFrame.setEnabled(false);
                sendTaskFrame.showFrame();
            }
        };
    }

    private AbstractAction sendTaskAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEmptyTitleAndDescription()) {
                    Task task = getInputTask();
                    TaskDAO.saveOrUpdate(task);
                    TableRefresher.refreshSelectedUsersTaskTable(bossTaskMenageFrame.getSelectedUser());
                    sendTaskFrame.dispose();
                    bossTaskMenageFrame.setEnabled(true);
                    bossTaskMenageFrame.toFront();
                }
            }
        };
    }

    private boolean isEmptyTitleAndDescription() {
        if(InputVerifier.textComponentIsEmpty(sendTaskFrame.getTitleTextField(), "Title")){
            ShowMessage.error("Title is empty.");
            return false;
        } else if (InputVerifier.textComponentIsEmpty(sendTaskFrame.getDescriptionTextArea(), "Description")){
            ShowMessage.error("Description is empty.");
            return false;
        } else return true;
    }

    private Task getInputTask() {
        String title = sendTaskFrame.getTaskTitle();
        String description = sendTaskFrame.getTaskDescription();
        LocalDate date = sendTaskFrame.getTaskDate();
        User employee = bossTaskMenageFrame.getSelectedUser();
        String senderNickName = MainFrame.getLoggedInUser().getNickname();
        return new Task(title, description, date, employee, senderNickName);
    }
}
