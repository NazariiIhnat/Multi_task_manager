package registration;

import components.custom.CustomFrame;
import components.custom.watermark.WatermarkPasswordField;
import components.custom.watermark.WatermarkTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;

@Component
public class RegistrationFrame extends CustomFrame {
    private WatermarkTextField nameTextField;
    private WatermarkTextField surnameTextField;
    private WatermarkTextField nicknameTextField;
    private WatermarkPasswordField passwordField;
    private WatermarkPasswordField repeatPasswordField;
    private JButton okButton;

    @Autowired
    RegistrationFrame() {
        super("Registration", 100, 100, 210, 225);
        initNameTextField();
        initSurnameTextField();
        initNicknameTextField();
        initPasswordField();
        initRepeatPasswordField();
        initOKButton();
    }

    private void initNameTextField() {
        nameTextField = new WatermarkTextField("Name");
        nameTextField.setBounds(10, 36, 190, 20);
        add(nameTextField);
    }

    private void initSurnameTextField() {
        surnameTextField = new WatermarkTextField("Surname");
        surnameTextField.setBounds(10, 67, 190, 20);
        add(surnameTextField);
    }

    private void initNicknameTextField() {
        nicknameTextField = new WatermarkTextField("Nickname");
        nicknameTextField.setBounds(10, 98, 190, 20);
        add(nicknameTextField);
    }

    private void initPasswordField() {
        passwordField = new WatermarkPasswordField("Password");
        passwordField.setBounds(10, 129, 190, 20);
        add(passwordField);
    }

    private void initRepeatPasswordField() {
        repeatPasswordField = new WatermarkPasswordField("Repeat password");
        repeatPasswordField.setBounds(10, 160, 190, 20);
        add(repeatPasswordField);
    }

    private void initOKButton() {
        okButton = new JButton("OK");
        okButton.setHorizontalAlignment(SwingConstants.CENTER);
        okButton.setAlignmentX(0.5f);
        okButton.setBounds(67, 192, 72, 23);
        add(okButton);
    }

    public void showFrame() {
        nameTextField.clearInputAndSetWatermark();
        surnameTextField.clearInputAndSetWatermark();
        nicknameTextField.clearInputAndSetWatermark();
        passwordField.clearInputAndSetWatermark();
        repeatPasswordField.clearInputAndSetWatermark();
        setVisible(true);
        toFront();
        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    public WatermarkTextField getNameTextField() {
        return nameTextField;
    }

    public WatermarkTextField getSurnameTextField() {
        return surnameTextField;
    }

    public WatermarkTextField getNicknameTextField() {
        return nicknameTextField;
    }

    public WatermarkPasswordField getPasswordField() {
        return passwordField;
    }

    public WatermarkPasswordField getRepeatPasswordField() {
        return repeatPasswordField;
    }

    public JButton getOkButton() {
        return okButton;
    }
}
