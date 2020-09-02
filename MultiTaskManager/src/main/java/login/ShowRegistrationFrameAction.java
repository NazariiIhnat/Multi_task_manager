package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import registration.RegistrationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ShowRegistrationFrameAction implements ActionListener {

    private LoginFrame loginFrame;
    private RegistrationFrame registrationFrame;

    @Autowired
    public ShowRegistrationFrameAction(LoginFrame loginFrame, RegistrationFrame registrationFrame) {
        this.loginFrame = loginFrame;
        this.registrationFrame = registrationFrame;
        loginFrame.getRegisterButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loginFrame.setVisible(false);
        registrationFrame.showFrame();
    }
}
