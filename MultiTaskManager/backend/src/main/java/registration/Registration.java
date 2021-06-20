package registration;

import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ShowMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Component
public class Registration {

    private RegistrationFrame frame;
    private InputValidator inputValidator;

    @Autowired
    public Registration(RegistrationFrame frame, InputValidator inputValidator) {
        this.frame = frame;
        this.inputValidator = inputValidator;
        frame.getOkButton().addActionListener(okButtonAction());
    }

    private AbstractAction okButtonAction() {
        return new AbstractAction() {
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
