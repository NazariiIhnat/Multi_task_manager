package work.boss_mode.send;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

import org.springframework.stereotype.Component;
import utils.Settings;
import utils.Watermarker;
import work.boss_mode.BossTaskMenageFrame;
import work.custom.components.CustomFrame;
import work.custom.components.DatePicker;

@Component
public class SendTaskFrame extends CustomFrame {

    private JTextField titleTextField;
    private JTextArea descriptionTextArea;
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

        titleTextField = new JTextField();
        titleTextField.setBounds(10, 36, 246, 22);
        Watermarker.addWatermarkToTextComponentWhenLostFocus(titleTextField, "Title");
        add(titleTextField);

        JScrollPane descriptionTextAreaScrollPane = new JScrollPane();
        descriptionTextAreaScrollPane.setBounds(10, 73, 246, 144);
        add(descriptionTextAreaScrollPane);

        descriptionTextArea = new JTextArea();
        Watermarker.addWatermarkToTextComponentWhenLostFocus(descriptionTextArea, "Description");
        descriptionTextAreaScrollPane.setViewportView(descriptionTextArea);

        sendButton = new JButton("SEND");
        sendButton.setBounds(96, 229, 72, 22);
        add(sendButton);

        setWatermarksToTextComponents();
        setLocationRelativeTo(getOwner());
    }

    private void setWatermarksToTextComponents() {
        titleTextField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        titleTextField.setText("Title");
        descriptionTextArea.setForeground(Settings.WATERMARK_TEXT_COLOR);
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
