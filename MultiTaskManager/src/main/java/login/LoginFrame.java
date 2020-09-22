package login;

import org.springframework.stereotype.Component;
import work.custom.components.CustomFrame;
import work.custom.components.WatermarkPasswordField;
import work.custom.components.WatermarkTextField;

import javax.swing.*;

@Component
public class LoginFrame extends CustomFrame {

    private WatermarkTextField nicknameTextField;
    private WatermarkPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        super("Login", 100, 100, 210, 132);
        setTitle("Login");
        initComponents();
    }

    private void initComponents() {
        nicknameTextField = new WatermarkTextField("Nickname");
        nicknameTextField.setBounds(10, 36, 190, 20);
        add(nicknameTextField);

        passwordField = new WatermarkPasswordField("Password");
        passwordField.setBounds(10, 67, 190, 20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 98, 87, 23);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(113, 98, 87, 23);
        add(registerButton);

        setWatermarksToFields();
        setVisible(true);
        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    void setWatermarksToFields() {
        nicknameTextField.setWatermark();
        passwordField.setWatermark();
    }

    public void showFrame() {
        setVisible(true);
        toFront();
        setWatermarksToFields();
        setLocationRelativeTo(getOwner());
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
