package registration;

import dao.UserDAO;
import entities.User;
import login.LoginFrame;
import message.ShowMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class Registration{

    private InputValidator inputValidator;
    private RegistrationFrame registrationFrame;
    private LoginFrame loginFrame;

    @Autowired
    public Registration(InputValidator inputValidator,
                        RegistrationFrame registrationFrame,
                        LoginFrame loginFrame) {
        this.inputValidator = inputValidator;
        this.registrationFrame = registrationFrame;
        this.loginFrame = loginFrame;
        loginFrame.getRegisterButton().addActionListener(showRegistrationFrameAction());
        registrationFrame.getOkButton().addActionListener(registrationAction());
    }

    private ActionListener showRegistrationFrameAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                registrationFrame.showFrame();
            }
        };
    }

    private ActionListener registrationAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = inputValidator.getUserIfValidInput();
                if(user != null) {
                    UserDAO.saveOrUpdate(user);
                    ShowMessage.success("Registration is completed");
                }
            }
        };
    }
}
