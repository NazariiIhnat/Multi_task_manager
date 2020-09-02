package login;

import utils.Settings;
import utils.Watermarker;
import org.springframework.stereotype.Component;
import work.custom.components.CustomFrame;

import javax.swing.*;

@Component
public class LoginFrame extends CustomFrame {

    private JTextField nicknameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        super("Login", 100, 100, 210, 132);
        setTitle("Login");
        initComponents();
    }

    private void initComponents() {
        nicknameTextField = new JTextField();
        Watermarker.addWatermarkToTextComponentWhenLostFocus(nicknameTextField, "Nickname");
        nicknameTextField.setBounds(10, 36, 190, 20);
        nicknameTextField.setColumns(10);
        add(nicknameTextField);

        passwordField = new JPasswordField();
        Watermarker.addWatermarkToPasswordFieldWhenLostFocus(passwordField, "Password");
        passwordField.setBounds(10, 67, 190, 20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 98, 87, 23);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(113, 98, 87, 23);
        add(registerButton);

        setLocationRelativeTo(getOwner());
        setWaterMarkTextToFields();
        setVisible(true);
        getContentPane().setFocusable(true);
    }

    private void setWaterMarkTextToFields() {
        nicknameTextField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        nicknameTextField.setText("Nickname");
        passwordField.setEchoChar((char)0);
        passwordField.setForeground(Settings.WATERMARK_TEXT_COLOR);
        passwordField.setText("Password");
    }

    public void showFrame() {
        setVisible(true);
        toFront();
        setLocationRelativeTo(getOwner());
        setWaterMarkTextToFields();
        getContentPane().setFocusable(true);
    }

    public JTextField getNicknameTextField() {
        return nicknameTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    @Override
    public void closeAction() {
        System.exit(0);
    }
}
