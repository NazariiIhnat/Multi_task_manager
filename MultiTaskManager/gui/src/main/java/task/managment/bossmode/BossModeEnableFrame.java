package task.managment.bossmode;

import components.custom.CustomFrame;
import components.custom.watermark.WatermarkPasswordField;
import org.springframework.stereotype.Component;
import javax.swing.*;

@Component
public class BossModeEnableFrame extends CustomFrame {

    private WatermarkPasswordField passwordField;
    private JButton okButton;

    public BossModeEnableFrame() {
        super("Boss mode", 100, 100, 194, 72);
        initPasswordField();
        initOkButton();
    }

    private void initPasswordField() {
        passwordField = new WatermarkPasswordField("Password");
        passwordField.setBounds(10, 37, 114, 22);
        add(passwordField);
    }

    private void initOkButton() {
        okButton = new JButton("OK");
        okButton.setBounds(136, 37, 48, 22);
        add(okButton);
    }

    public void showFrame() {
        setVisible(true);
        passwordField.clearInputAndSetWatermark();
    }

    public WatermarkPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getOkButton() {
        return okButton;
    }
}
