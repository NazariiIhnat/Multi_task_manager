package task.managment.bossmode;

import components.custom.CustomFrame;
import components.custom.DatePicker;
import components.custom.watermark.WatermarkTextArea;
import components.custom.watermark.WatermarkTextField;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

@Component
public class TaskSendFrame extends CustomFrame {

    private WatermarkTextField titleTextField;
    private WatermarkTextArea descriptionTextArea;
    private DatePicker datePicker;
    private JButton sendButton;

    public TaskSendFrame() {
        super("Send task", 100, 100, 511, 263);
        initDatePicker();
        initTitleTextField();
        initDescriptionTextArea();
        initSendButton();
        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    private void initDatePicker() {
        datePicker = new DatePicker();
        datePicker.getSettings().setVisibleClearButton(false);
        datePicker.setBounds(new Rectangle(0, 0, 234, 215));
        datePicker.setBounds(266, 36, 234, 215);
        add(datePicker);
    }

    private void initTitleTextField() {
        titleTextField = new WatermarkTextField("Title");
        titleTextField.setBounds(10, 36, 246, 22);
        add(titleTextField);
    }

    private void initDescriptionTextArea() {
        JScrollPane descriptionTextAreaScrollPane = new JScrollPane();
        descriptionTextAreaScrollPane.setBounds(10, 73, 246, 144);
        add(descriptionTextAreaScrollPane);
        descriptionTextArea = new WatermarkTextArea("Description");
        descriptionTextAreaScrollPane.setViewportView(descriptionTextArea);
    }

    private void initSendButton() {
        sendButton = new JButton("SEND");
        sendButton.setBounds(96, 229, 72, 22);
        add(sendButton);
    }

    public void showFrame() {
        setVisible(true);
        titleTextField.clearInputAndSetWatermark();
        descriptionTextArea.clearInputAndSetWatermark();
        datePicker.setSelectedDate(LocalDate.now());
    }

    public WatermarkTextField getTitleTextField() {
        return titleTextField;
    }

    public WatermarkTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public JButton getSendButton() {
        return sendButton;
    }
}
