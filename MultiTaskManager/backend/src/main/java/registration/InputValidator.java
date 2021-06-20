package registration;

import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.InputVerifier;
import utils.ShowMessage;

@Component
public class InputValidator {

    private RegistrationFrame frame;
    private String name;
    private String surname;
    private String nickname;
    private String password;
    private String confirmPassword;

    @Autowired
    public InputValidator(RegistrationFrame frame) {
        this.frame = frame;
    }

    User getUserIfValidInput() {
        if(fieldsIsNotEmpty()) {
            readInput();
            if (isUniqueNickname() && passwordIsLongerThanEightChars() && passwordIsConfirmed())
                return new User(name, surname, nickname, password);
        } else return null;
        return null;
    }

    private boolean fieldsIsNotEmpty () {
        if (InputVerifier.textComponentIsEmpty(frame.getNameTextField(), "Name")) {
            ShowMessage.error("Name is empty");
            return false;
        } else if (InputVerifier.textComponentIsEmpty(frame.getSurnameTextField(), "Surname")) {
            ShowMessage.error("Surname is empty");
            return false;
        } else if (InputVerifier.textComponentIsEmpty(frame.getNicknameTextField(), "Nickname")){
            ShowMessage.error("Nickname is empty");
            return false;
        } else if (InputVerifier.textComponentIsEmpty(frame.getPasswordField(), "Password")) {
            ShowMessage.error("Password is empty");
            return false;
        } else if (InputVerifier.textComponentIsEmpty(frame.getRepeatPasswordField(), "Repeat password")) {
            ShowMessage.error("Confirm your password");
            return false;
        } else {
            return true;
        }
    }

    private void readInput() {
        this.name = frame.getNameTextField().getText();
        this.surname = frame.getSurnameTextField().getText();
        this.nickname = frame.getNicknameTextField().getText();
        this.password = new String(frame.getPasswordField().getPassword());
        this.confirmPassword = new String(frame.getRepeatPasswordField().getPassword());
    }

    private boolean isUniqueNickname() {
        User user = UserDAO.getUserByNickname(nickname);
        if(user != null){
            ShowMessage.error("An account with nickname " + nickname + " already exists ");
            return false;
        }
        else return true;
    }

    private boolean passwordIsLongerThanEightChars() {
        if(password.toCharArray().length < 8){
            ShowMessage.error("Password is less than 8 characters");
            return false;
        }
        else return true;
    }

    private boolean passwordIsConfirmed() {
        if(!this.password.equals(this.confirmPassword)){
            ShowMessage.error("Confirm your password");
            return false;
        }
        else
            return true;
    }
}
