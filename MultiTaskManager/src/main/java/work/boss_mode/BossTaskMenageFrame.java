package work.boss_mode;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Settings;
import utils.TableRefresher;
import work.custom.components.CustomFrame;
import work.main.MainFrame;

@Component
public class BossTaskMenageFrame extends CustomFrame {

    private JList<String> employeesList;
    private BossModeTaskTable bossModeTaskTable;
    private MainFrame mainFrame;
    private static JMenuItem sendTaskItem;
    private JPopupMenu employeeListPopupMenu;

    @Autowired
    public BossTaskMenageFrame(BossModeTaskTable bossModeTaskTable, MainFrame mainFrame) {
        super("Employees tasks", 100, 100, 479, 300);
        this.bossModeTaskTable = bossModeTaskTable;
        this.employeesList = new JList<>();
        this.mainFrame = mainFrame;
        this.employeeListPopupMenu = new JPopupMenu();
        this.employeesList.addListSelectionListener(loadTasks());
        initComponents();
        initSendMenuItem();
        addPopupMenuToEmployeeList();
    }

    private ListSelectionListener loadTasks() {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    User user = getSelectedUser();
                    TableRefresher.refreshSelectedUsersTaskTable(user);
                }
            }
        };
    }

    public User getSelectedUser() {
        String nickname = employeesList.getSelectedValue().split("@")[1];
        return UserDAO.getUserByNickname(nickname);
    }

    private void initComponents() {
        JScrollPane employeeListScrollPane = new JScrollPane();
        employeeListScrollPane.setBounds(10, 35, 156, 255);
        add(employeeListScrollPane);
        employeeListScrollPane.setViewportView(employeesList);

        bossModeTaskTable.getScrollPane().setBounds(178, 35, 293, 175);
        add(bossModeTaskTable.getScrollPane());

        JTextArea descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setForeground(Settings.WATERMARK_TEXT_COLOR);
        descriptionTextArea.setText("Description");

        JScrollPane descriptionScrollPane = new JScrollPane();
        descriptionScrollPane.setViewportView(descriptionTextArea);
        descriptionScrollPane.setBounds(178, 220, 293, 70);
        add(descriptionScrollPane);

        bossModeTaskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    descriptionTextArea.setForeground(Settings.TEXT_COLOR);
                    String text = (String) bossModeTaskTable.getValueAt(bossModeTaskTable.getSelectedRow(), 4);
                    descriptionTextArea.setText(text);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    descriptionTextArea.setForeground(Settings.WATERMARK_TEXT_COLOR);
                    descriptionTextArea.setText("Description");
                }
            }
        });

        setLocationRelativeTo(getOwner());
    }

    private void initSendMenuItem() {
        sendTaskItem = new JMenuItem("Send task");
        sendTaskItem.addActionListener(sendTaskMenuItemAction());
        mainFrame.addItemToEmployeesMenu(sendTaskItem);
    }

    private AbstractAction sendTaskMenuItemAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                loadUsers();
            }
        };
    }

    private void loadUsers() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<User> userList = UserDAO.getAllUsers();
        int counter = 1;
        for(User user : userList) {
            if(!user.getNickname().equals(MainFrame.getLoggedInUser().getNickname())) {
                listModel.addElement(counter + ". " + user.getName() + " " + user.getSurname() + " @" + user.getNickname());
                counter++;
            }
        }
        employeesList.setModel(listModel);
    }
    private void addPopupMenuToEmployeeList() {
        employeesList.setComponentPopupMenu(employeeListPopupMenu);
    }

    public BossModeTaskTable getBossModeTaskTable() {
        return bossModeTaskTable;
    }

    JMenuItem getSendTaskItem() {
        return sendTaskItem;
    }

    public static void setSendTaskItemEnabled(boolean flag) {
        sendTaskItem.setEnabled(flag);
    }

    public void addMenuItemToEmployeeListPopupMenu(JMenuItem item) {
        employeeListPopupMenu.add(item);
    }

    @Override
    public void closeAction() {
        employeesList.setSelectedIndex(-1);
        TableRefresher.nullifyTaskTable(bossModeTaskTable);
        dispose();
        mainFrame.toFront();
        mainFrame.setEnabled(true);
    }
}
