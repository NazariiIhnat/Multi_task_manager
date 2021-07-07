package task.managment;

import javax.swing.*;

public class Menu extends JMenuBar{

    private JMenu profileMenu, employeeMenu;
    private JMenuItem logoutMenuItem, bossModeMenuItem, taskManagementMenuItem;

    Menu() {
        profileMenu = new JMenu("Profile");
        employeeMenu = new JMenu("Employee");
        add(profileMenu);
        add(employeeMenu);
        initLogoutMenuItem();
        initBossModeMenuItem();
        initTaskManagementMenuItem();
    }

    private void initLogoutMenuItem() {
        logoutMenuItem = new JMenuItem("Logout");
        profileMenu.add(logoutMenuItem);
    }

    private void initBossModeMenuItem() {
        bossModeMenuItem = new JMenuItem("Boss mode");
        profileMenu.add(bossModeMenuItem);
    }

    private void initTaskManagementMenuItem() {
        taskManagementMenuItem = new JMenuItem("Task management");
        employeeMenu.add(taskManagementMenuItem);
    }

    public JMenu getProfileMenu() {
        return profileMenu;
    }

    public JMenu getEmployeeMenu() {
        return employeeMenu;
    }

    public JMenuItem getLogoutMenuItem() {
        return logoutMenuItem;
    }

    public JMenuItem getBossModeMenuItem() {
        return bossModeMenuItem;
    }

    public JMenuItem getTaskManagementMenuItem() {
        return taskManagementMenuItem;
    }
}
