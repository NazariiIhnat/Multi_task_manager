package task.managment.bossmode;

import components.custom.Colors;
import components.custom.CustomFrame;
import components.custom.watermark.WatermarkTextArea;
import dao.UserDAO;
import entities.User;
import org.springframework.stereotype.Component;
import javax.swing.*;

@Component
public class BossModeFrame extends CustomFrame {

    private JList<String> employeesList;
    private WatermarkTextArea descriptionTextArea;
    private BossModeTaskTable bossModeTaskTable;
    private JPopupMenu popupMenu;

    public BossModeFrame() {
        super("Employees tasks", 100, 100, 479, 300);
        popupMenu = new JPopupMenu();
        initEmployeeList();
        initTable();
        initDescriptionTextArea();
        initPopupMenu();
    }

    private void initEmployeeList() {
        employeesList = new JList<>();
        JScrollPane employeeListScrollPane = new JScrollPane();
        employeeListScrollPane.setBounds(10, 35, 156, 255);
        add(employeeListScrollPane);
        employeeListScrollPane.setViewportView(employeesList);
    }

    private void initTable() {
        bossModeTaskTable = new BossModeTaskTable();
        bossModeTaskTable.getScrollPane().setBounds(178, 35, 293, 175);
        add(bossModeTaskTable.getScrollPane());
    }

    private void initDescriptionTextArea() {
        descriptionTextArea = new WatermarkTextArea("Description");
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setForeground(Colors.WATERMARK_TEXT_COLOR);
        descriptionTextArea.setText("Description");
        JScrollPane descriptionScrollPane = new JScrollPane();
        descriptionScrollPane.setViewportView(descriptionTextArea);
        descriptionScrollPane.setBounds(178, 220, 293, 70);
        add(descriptionScrollPane);
    }

    private void initPopupMenu() {
        popupMenu = new JPopupMenu();
        employeesList.setComponentPopupMenu(popupMenu);
    }

    public User getSelectedUser() {
        String nickname = employeesList.getSelectedValue().split("@")[1];
        return UserDAO.getUserByNickname(nickname);
    }
    public JList<String> getEmployeesList() {
        return employeesList;
    }

    public WatermarkTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public BossModeTaskTable getBossModeTaskTable() {
        return bossModeTaskTable;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
