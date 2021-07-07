package task.managment;

import components.custom.CustomFrame;
import components.custom.watermark.WatermarkTextArea;
import components.custom.watermark.WatermarkTextField;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class TaskAddFrame extends CustomFrame {

    private WatermarkTextField titleTextField;
    private WatermarkTextArea descriptionTextArea;
    private JButton okButton;

    public TaskAddFrame() {
        super("Add task", 100, 100, 266, 292);
        initTitleTextField();
        initDescriptionTextArea();
        initOKButton();
        setLocationRelativeTo(getOwner());
    }

    private void initTitleTextField() {
        titleTextField = new WatermarkTextField("Title");
        titleTextField.setBounds(10, 36, 246, 22);
        add(titleTextField);
    }

    private void initDescriptionTextArea() {
        descriptionTextArea = new WatermarkTextArea("Description");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(descriptionTextArea);
        scrollPane.setBounds(10, 71, 246, 177);
        add(scrollPane);
    }

    private void initOKButton() {
        okButton = new JButton("OK");
        okButton.setBounds(87, 259, 93, 22);
        add(okButton);
    }

    public void showFrame() {
        setVisible(true);
        titleTextField.clearInputAndSetWatermark();
        descriptionTextArea.clearInputAndSetWatermark();
    }

    public JButton getOkButton() {
        return okButton;
    }

    public WatermarkTextField getTitleTextField() {
        return titleTextField;
    }

    public WatermarkTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
}
