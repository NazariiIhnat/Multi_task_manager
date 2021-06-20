package work.main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components.Colors;
import components.custom.CustomFrame;
import components.custom.DatePicker;
import entities.User;
import login.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;


@Component
public class MainFrame extends CustomFrame {

    private static User loggedInUser;
    private OwnTaskTable ownTaskTable;
    private DatePicker datePicker;
    private LoginFrame loginFrame;

    private JMenuBar menuBar;
    private JMenu profileMenu;
    private JMenu employeesMenu;
    private JTextArea descriptionTextArea;

    @Autowired
    public MainFrame(OwnTaskTable ownTaskTable, DatePicker datePicker, LoginFrame loginFrame) {
        super("Joji", 100, 100, 637, 282);
        this.ownTaskTable = ownTaskTable;
        this.datePicker = datePicker;
        this.loginFrame = loginFrame;
        initMenu();
        initComponents();
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 25, 637, 21);
        profileMenu = new JMenu("Profile");
        employeesMenu = new JMenu("Employees");
        menuBar.add(profileMenu);
        menuBar.add(employeesMenu);
    }

    private void initComponents() {
        ownTaskTable.getTaskTableScrollPane().setBounds(10, 57, 373, 136);
        add(ownTaskTable.getTaskTableScrollPane());
        ownTaskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    descriptionTextArea.setForeground(Colors.TEXT_COLOR);
                    String text = (String) ownTaskTable.getValueAt(ownTaskTable.getSelectedRow(), 5);
                    descriptionTextArea.setText(text);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    descriptionTextArea.setForeground(Colors.WATERMARK_TEXT_COLOR);
                    descriptionTextArea.setText("Description");
                }
            }
        });

        datePicker.setBounds(393, 57, 234, 215);
        add(datePicker);
        add(menuBar);
        menuBar.add(profileMenu);
        menuBar.add(employeesMenu);
        setLocationRelativeTo(getOwner());

        JScrollPane descriptionScrollPane = new JScrollPane();
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setText("Description");
        descriptionTextArea.setForeground(Colors.WATERMARK_TEXT_COLOR);
        descriptionScrollPane.setViewportView(descriptionTextArea);
        descriptionScrollPane.setBounds(10, 203, 373, 69);
        add(descriptionScrollPane);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        MainFrame.loggedInUser = loggedInUser;
    }

    public void addItemToProfileMenu(JMenuItem item) {
        profileMenu.add(item);
    }

    public void addItemToEmployeesMenu(JMenuItem item) {
        employeesMenu.add(item);
    }

    public OwnTaskTable getOwnTaskTable() {
        return ownTaskTable;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDescriptionText(String text) {
        descriptionTextArea.setText(text);
    }

    @Override
    public void closeAction() {
        for(Frame frame : Frame.getFrames())
            frame.dispose();
        setLoggedInUser(null);
        loginFrame.showFrame();
    }
}
