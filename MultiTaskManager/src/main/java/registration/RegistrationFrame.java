package registration;
import login.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Settings;
import utils.Watermarker;
import work.custom.components.CustomFrame;

import javax.swing.*;

@Component
public class RegistrationFrame extends CustomFrame {

    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField nicknameTextField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JButton okButton;
    private LoginFrame loginFrame;

    @Autowired
    RegistrationFrame(LoginFrame loginFrame) {
        super("Registration", 100, 100, 210, 225);
        this.loginFrame = loginFrame;
        initComponents();
    }

    private void initComponents() {
        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        nameTextField.setBounds(10, 36, 190, 20);
        Watermarker.addWatermarkToTextComponentWhenLostFocus(nameTextField, "Name");
        add(nameTextField);

        surnameTextField = new JTextField();
        surnameTextField.setColumns(10);
        surnameTextField.setBounds(10, 67, 190, 20);
        Watermarker.addWatermarkToTextComponentWhenLostFocus(surnameTextField, "Surname");
        add(surnameTextField);

        nicknameTextField = new JTextField();
        nicknameTextField.setColumns(10);
        nicknameTextField.setBounds(10, 98, 190, 20);
        Watermarker.addWatermarkToTextComponentWhenLostFocus(nicknameTextField, "Nickname");
        add(nicknameTextField);

        passwordField = new JPasswordField();
        passwordField.setBounds(10, 129, 190, 20);
        Watermarker.addWatermarkToPasswordFieldWhenLostFocus(passwordField, "Password");
        add(passwordField);

        repeatPasswordField = new JPasswordField();
        repeatPasswordField.setBounds(10, 160, 190, 20);
        Watermarker.addWatermarkToPasswordFieldWhenLostFocus(repeatPasswordField, "Repeat password");
        add(repeatPasswordField);

        okButton = new JButton("OK");
        okButton.setHorizontalAlignment(SwingConstants.CENTER);
        okButton.setAlignmentX(0.5f);
        okButton.setBounds(67, 192, 72, 23);
        add(okButton);

        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    public void setWaterMarksToFields() {
        nameTextField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        nameTextField.setText("Name");
        surnameTextField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        surnameTextField.setText("Surname");
        nicknameTextField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        nicknameTextField.setText("Nickname");
        passwordField.setEchoChar((char)0);
        passwordField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        passwordField.setText("Password");
        repeatPasswordField.setEchoChar((char)0);
        repeatPasswordField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        repeatPasswordField.setText("Repeat password");
    }

    public void showFrame() {
        setVisible(true);
        toFront();
        setLocationRelativeTo(getOwner());
        setWaterMarksToFields();
        getContentPane().setFocusable(true);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

    public JTextField getNicknameTextField() {
        return nicknameTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getRepeatPasswordField() {
        return repeatPasswordField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    @Override
    public void closeAction() {
        dispose();
        loginFrame.showFrame();
    }
}
