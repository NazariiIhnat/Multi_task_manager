package components.custom;

import javax.swing.*;
import com.github.lgooddatepicker.components.*;
import components.custom.watermark.WatermarkTextArea;
import components.custom.watermark.WatermarkTextField;
import entities.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskUpdateFrame extends CustomFrame {

    private components.custom.DatePicker datePicker;
    private WatermarkTextField titleTextField;
    private WatermarkTextArea descriptionTextArea;
    private JButton saveButton;

    public TaskUpdateFrame() {
        super("Update", 100, 100, 507, 300);
        initDatePicker();
        initSaveButton();
        initTitleTextField();
        initDescriptionTextArea();
    }

    private void initDatePicker() {
        datePicker = new DatePicker();
        datePicker.getSettings().setVisibleClearButton(false);
        datePicker.setBounds(263, 36, 234, 215);
        add(datePicker);
    }

    private void initSaveButton() {
        saveButton = new JButton("SAVE");
        saveButton.setBounds(209, 266, 89, 23);
        add(saveButton);
    }

    private void initTitleTextField() {
        titleTextField = new WatermarkTextField("Title");
        titleTextField.setBounds(10, 36, 243, 20);
        titleTextField.setColumns(10);
        add(titleTextField);
    }

    private void initDescriptionTextArea() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 67, 243, 184);
        add(scrollPane);
        descriptionTextArea = new WatermarkTextArea("Description");
        scrollPane.setViewportView(descriptionTextArea);
        setLocationRelativeTo(getOwner());
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

    public JButton getSaveButton() {
        return saveButton;
    }

    public CalendarPanel getCalendarPanel() {
        return datePicker;
    }

    public WatermarkTextField getTitleTextField() {
        return titleTextField;
    }

    public WatermarkTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
}
