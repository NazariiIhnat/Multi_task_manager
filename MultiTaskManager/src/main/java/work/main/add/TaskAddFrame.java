package work.main.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Settings;
import work.custom.components.CustomFrame;
import work.custom.components.WatermarkTextArea;
import work.custom.components.WatermarkTextField;
import work.main.MainFrame;

import javax.swing.*;

@Component
public class TaskAddFrame extends CustomFrame {

    private MainFrame mainFrame;
    private WatermarkTextField titleTextField;
    private WatermarkTextArea descriptionTextArea;
    private JButton okButton;

    @Autowired
    public TaskAddFrame(MainFrame mainFrame) {
        super("Add task", 100, 100, 266, 292);
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {

        titleTextField = new WatermarkTextField("Title");
        titleTextField.setBounds(10, 36, 246, 22);
        add(titleTextField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 71, 246, 177);
        add(scrollPane);

        descriptionTextArea = new WatermarkTextArea("Description");
        scrollPane.setViewportView(descriptionTextArea);

        okButton = new JButton("OK");
        okButton.setBounds(87, 259, 93, 22);
        add(okButton);
        setLocationRelativeTo(getOwner());
    }

    void showFrame() {
        setVisible(true);
        titleTextField.setText("Title");
        titleTextField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        descriptionTextArea.setText("Description");
        descriptionTextArea.setForeground(Settings.WATERMARK_TEXT_COLOR);
    }

    JButton getOkButton() {
        return okButton;
    }

    public String getTitle() {
        return titleTextField.getText();
    }

    public String getDescription() {
        return descriptionTextArea.getText();
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
        mainFrame.setEnabled(true);
        mainFrame.toFront();
    }
}
