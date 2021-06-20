package work.boss_mode.send;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

import components.Colors;
import components.custom.CustomFrame;
import components.custom.DatePicker;
import components.custom.watermark.WatermarkTextArea;
import components.custom.watermark.WatermarkTextField;
import org.springframework.stereotype.Component;
import work.boss_mode.BossTaskMenageFrame;

@Component
public class SendTaskFrame extends CustomFrame {

    private WatermarkTextField titleTextField;
    private WatermarkTextArea descriptionTextArea;
    private DatePicker datePicker;
    private JButton sendButton;
    private BossTaskMenageFrame bossTaskMenageFrame;

    public SendTaskFrame(BossTaskMenageFrame bossTaskMenageFrame) {
        super("Send task", 100, 100, 511, 263);
        this.bossTaskMenageFrame = bossTaskMenageFrame;
        initComponents();
    }

    private void initComponents() {
        datePicker = new DatePicker();
        datePicker.getSettings().setVisibleClearButton(false);
        datePicker.setBounds(new Rectangle(0, 0, 234, 215));
        datePicker.setBounds(266, 36, 234, 215);
        add(datePicker);

        titleTextField = new WatermarkTextField("Title");
        titleTextField.setBounds(10, 36, 246, 22);
        add(titleTextField);

        JScrollPane descriptionTextAreaScrollPane = new JScrollPane();
        descriptionTextAreaScrollPane.setBounds(10, 73, 246, 144);
        add(descriptionTextAreaScrollPane);

        descriptionTextArea = new WatermarkTextArea("Description");
        descriptionTextAreaScrollPane.setViewportView(descriptionTextArea);

        sendButton = new JButton("SEND");
        sendButton.setBounds(96, 229, 72, 22);
        add(sendButton);

        setWatermarksToTextComponents();
        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    private void setWatermarksToTextComponents() {
        titleTextField.setForeground(Colors.WATERMARK_TEXT_COLOR);
        titleTextField.setText("Title");
        descriptionTextArea.setForeground(Colors.WATERMARK_TEXT_COLOR);
        descriptionTextArea.setText("Description");
    }

    public void showFrame() {
        setVisible(true);
        setWatermarksToTextComponents();
        datePicker.setSelectedDate(LocalDate.now());
    }

    String getTaskTitle() {
        return titleTextField.getText();
    }

    String getTaskDescription() {
        return descriptionTextArea.getText();
    }

    LocalDate getTaskDate() {
        return datePicker.getSelectedDate();
    }

    void setSendButtonListener(AbstractAction abstractAction) {
        sendButton.addActionListener(abstractAction);
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    @Override
    public void closeAction() {
        dispose();
        bossTaskMenageFrame.setEnabled(true);
        bossTaskMenageFrame.toFront();
    }
}
