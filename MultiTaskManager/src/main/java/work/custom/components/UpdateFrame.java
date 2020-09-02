package work.custom.components;

import javax.swing.*;

import com.github.lgooddatepicker.components.*;
import entities.Task;

public abstract class UpdateFrame extends CustomFrame {

    private DatePicker datePicker;
    private JTextField titleTextField;
    private JTextArea descriptionTextArea;
    private JButton saveButton;

    public UpdateFrame() {
        super("Update", 100, 100, 507, 300);
        initComponents();
    }

    private void initComponents() {
        datePicker = new DatePicker();
        datePicker.getSettings().setVisibleClearButton(false);
        datePicker.setBounds(263, 36, 234, 215);
        add(datePicker);

        saveButton = new JButton("SAVE");
        saveButton.setBounds(209, 266, 89, 23);
        add(saveButton);

        titleTextField = new JTextField();
        titleTextField.setBounds(10, 36, 243, 20);
        add(titleTextField);
        titleTextField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 67, 243, 184);
        add(scrollPane);

        descriptionTextArea = new JTextArea();
        scrollPane.setViewportView(descriptionTextArea);
        setLocationRelativeTo(getOwner());
    }

    protected void clearFields() {
        titleTextField.setText(null);
        descriptionTextArea.setText(null);
        datePicker.setSelectedDate(null);
    }

    public void loadTaskToUpdateFrame(Task task) {
        datePicker.setSelectedDate(task.getDate());
        titleTextField.setText(task.getTitle());
        descriptionTextArea.setText(task.getDescription());
    }

    protected JButton getSaveButton() {
        return saveButton;
    }

    protected CalendarPanel getCalendarPanel() {
        return datePicker;
    }

    protected JTextField getTitleTextField() {
        return titleTextField;
    }

    protected JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
}
