package task.managment;

import javax.swing.*;
import components.custom.Colors;
import components.custom.CustomFrame;
import components.custom.DatePicker;
import components.custom.watermark.WatermarkTextArea;
import entities.User;
import org.springframework.stereotype.Component;

@Component
public class WorkFrame extends CustomFrame {

    private static User loggedInUser;
    private TaskManageTable taskManageTable;
    private DatePicker datePicker;
    private Menu menu;
    private WatermarkTextArea descriptionTextArea;

    public WorkFrame() {
        super("Joji", 100, 100, 637, 282);
        initTaskManageTable();
        initMenu();
        initDatePicker();
        initDescriptionTextArea();
    }

    private void initMenu() {
        menu = new Menu();
        menu.setBounds(0, 25, getWidth(), 21);
        add(menu);
    }

    private void initTaskManageTable() {
        taskManageTable = new TaskManageTable();
        taskManageTable.getTaskTableScrollPane().setBounds(10, 57, 373, 136);
        add(taskManageTable.getTaskTableScrollPane());
    }

    private void initDatePicker() {
        datePicker = new DatePicker();
        datePicker.setBounds(393, 57, 234, 215);
        add(datePicker);
    }

    private void initDescriptionTextArea() {
        JScrollPane descriptionScrollPane = new JScrollPane();
        descriptionTextArea = new WatermarkTextArea("Description");
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setForeground(Colors.WATERMARK_TEXT_COLOR);
        descriptionScrollPane.setViewportView(descriptionTextArea);
        descriptionScrollPane.setBounds(10, 203, 373, 69);
        add(descriptionScrollPane);
    }

    public void showBossModeFunctions() {
        menu.getEmployeeMenu().setVisible(true);
    }

    public void hideBossModeFunctions() {
        menu.getEmployeeMenu().setVisible(false);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        WorkFrame.loggedInUser = loggedInUser;
    }

    public Menu getMenu() {
        return menu;
    }

    public TaskManageTable getTaskManageTable() {
        return taskManageTable;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public WatermarkTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
}
