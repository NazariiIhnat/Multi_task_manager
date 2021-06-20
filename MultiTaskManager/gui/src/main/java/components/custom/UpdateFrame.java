package components.custom;

import javax.swing.*;

import com.github.lgooddatepicker.components.*;
import components.Colors;
import components.custom.watermark.WatermarkTextArea;
import components.custom.watermark.WatermarkTextField;
import entities.Task;

public abstract class UpdateFrame extends CustomFrame {

    private DatePicker datePicker;
    private WatermarkTextField titleTextField;
    private WatermarkTextArea descriptionTextArea;
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

        titleTextField = new WatermarkTextField("Title");
        titleTextField.setBounds(10, 36, 243, 20);
        add(titleTextField);
        titleTextField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 67, 243, 184);
        add(scrollPane);

        descriptionTextArea = new WatermarkTextArea("Description");
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

    public void setVisible(boolean flag) {
        super.setVisible(flag);
        this.titleTextField.setForeground(Colors.TEXT_COLOR);
        this.descriptionTextArea.setForeground(Colors.TEXT_COLOR);
    }

    protected JButton getSaveButton() {
        return saveButton;
    }

    protected CalendarPanel getCalendarPanel() {
        return datePicker;
    }

    protected WatermarkTextField getTitleTextField() {
        return titleTextField;
    }

    protected WatermarkTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
}
