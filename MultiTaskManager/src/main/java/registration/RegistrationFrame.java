package registration;
import login.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.custom.components.CustomFrame;
import work.custom.components.WatermarkPasswordField;
import work.custom.components.WatermarkTextField;

import javax.swing.*;

@Component
public class RegistrationFrame extends CustomFrame {

    private WatermarkTextField nameTextField;
    private WatermarkTextField surnameTextField;
    private WatermarkTextField nicknameTextField;
    private WatermarkPasswordField passwordField;
    private WatermarkPasswordField repeatPasswordField;
    private JButton okButton;
    private LoginFrame loginFrame;

    @Autowired
    RegistrationFrame(LoginFrame loginFrame) {
        super("Registration", 100, 100, 210, 225);
        this.loginFrame = loginFrame;
        initComponents();
    }

    private void initComponents() {
        nameTextField = new WatermarkTextField("Name");
        nameTextField.setBounds(10, 36, 190, 20);
        add(nameTextField);

        surnameTextField = new WatermarkTextField("Surname");
        surnameTextField.setBounds(10, 67, 190, 20);
        add(surnameTextField);

        nicknameTextField = new WatermarkTextField("Nickname");
        nicknameTextField.setBounds(10, 98, 190, 20);
        add(nicknameTextField);

        passwordField = new WatermarkPasswordField("Password");
        passwordField.setBounds(10, 129, 190, 20);
        add(passwordField);

        repeatPasswordField = new WatermarkPasswordField("Repeat password");
        repeatPasswordField.setBounds(10, 160, 190, 20);
        add(repeatPasswordField);

        okButton = new JButton("OK");
        okButton.setHorizontalAlignment(SwingConstants.CENTER);
        okButton.setAlignmentX(0.5f);
        okButton.setBounds(67, 192, 72, 23);
        add(okButton);

        setWaterMarksToFields();
        setLocationRelativeTo(getOwner());
        getContentPane().setFocusable(true);
    }

    void setWaterMarksToFields() {
        nameTextField.setWatermark();
        surnameTextField.setWatermark();
        nicknameTextField.setWatermark();
        passwordField.setWatermark();
        repeatPasswordField.setWatermark();
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
