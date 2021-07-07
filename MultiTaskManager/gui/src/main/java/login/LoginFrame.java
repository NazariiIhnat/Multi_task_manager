package login;

import components.custom.CustomFrame;
import components.custom.watermark.WatermarkPasswordField;
import components.custom.watermark.WatermarkTextField;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class LoginFrame extends CustomFrame {

    private WatermarkTextField nicknameTextField;
    private WatermarkPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        super("Login", 100, 100, 210, 132);
        initNicknameTextField();
        initPasswordField();
        initLoginButton();
        initRegistrationButton();
        showFrame();
    }

    private void initNicknameTextField() {
        nicknameTextField = new WatermarkTextField("Nickname");
        nicknameTextField.setBounds(10, 36, 190, 20);
        add(nicknameTextField);
    }

    private void initPasswordField() {
        passwordField = new WatermarkPasswordField("Password");
        passwordField.setBounds(10, 67, 190, 20);
        add(passwordField);
    }

    private void initLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 98, 87, 23);
        add(loginButton);
    }

    private void initRegistrationButton() {
        registerButton = new JButton("Register");
        registerButton.setBounds(113, 98, 87, 23);
        add(registerButton);
    }

    public void showFrame() {
        nicknameTextField.clearInputAndSetWatermark();
        passwordField.clearInputAndSetWatermark();
        setVisible(true);
        toFront();
        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    public void clearTextComponentsAndSetWatermarks() {
        nicknameTextField.clearInputAndSetWatermark();
        passwordField.clearInputAndSetWatermark();
    }

    public WatermarkTextField getNicknameTextField() {
        return nicknameTextField;
    }

    public WatermarkPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
