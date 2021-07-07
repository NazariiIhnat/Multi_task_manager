package task.managment.bossmode;

import components.custom.Colors;
import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.WorkFrame;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.List;

@Component
public class UsersAndTasksLoader {

    private BossModeFrame bossModeFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public UsersAndTasksLoader(WorkFrame workFrame, BossModeFrame bossModeFrame, TableRefresher tableRefresher) {
        this.bossModeFrame = bossModeFrame;
        this.tableRefresher = tableRefresher;
        workFrame.getMenu().getTaskManagementMenuItem().addActionListener(taskManagementMenuItemAction());
        bossModeFrame.getEmployeesList().addListSelectionListener(loadSelectedUserTasks());
        bossModeFrame.getBossModeTaskTable().getSelectionModel().addListSelectionListener(loadSelectedTaskDescription());
    }

    private AbstractAction taskManagementMenuItemAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bossModeFrame.setVisible(true);
                loadUsers();
            }
        };
    }

    private void loadUsers() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<User> userList = UserDAO.getAllUsers();
        int counter = 1;
        for(User user : userList) {
            if(!user.getNickname().equals(WorkFrame.getLoggedInUser().getNickname())) {
                listModel.addElement(counter + ". " + user.getName() + " " + user.getSurname() + " @" + user.getNickname());
                counter++;
            }
        }
        bossModeFrame.getEmployeesList().setModel(listModel);
    }

    private ListSelectionListener loadSelectedUserTasks() {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    User user = bossModeFrame.getSelectedUser();
                    tableRefresher.refreshSelectedUsersTaskTable(user);
                }
            }
        };
    }

    private ListSelectionListener loadSelectedTaskDescription() {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    bossModeFrame.getDescriptionTextArea().setForeground(Colors.TEXT_COLOR);
                    String text = bossModeFrame.getBossModeTaskTable().getSelectedTask().getDescription();
                    bossModeFrame.getDescriptionTextArea().setText(text);
                } catch (ArrayIndexOutOfBoundsException | NullPointerException ex2) {
                    bossModeFrame.getDescriptionTextArea().clearInputAndSetWatermark();
                }
            }
        };
    }
}
