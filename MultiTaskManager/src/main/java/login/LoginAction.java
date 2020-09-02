package login;

import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Settings;
import utils.ShowMessage;
import utils.TableRefresher;
import work.main.MainFrame;
import work.boss_mode.BossTaskMenageFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LoginAction implements ActionListener {

    private LoginFrame loginFrame;
    private MainFrame mainFrame;

    @Autowired
    public LoginAction(LoginFrame loginFrame, MainFrame mainFrame) {
        this.loginFrame = loginFrame;
        this.mainFrame = mainFrame;
        loginFrame.getLoginButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname = getInputNickname();
        String password = getInputPassword();
        User user = UserDAO.getLoggedInUser(nickname, password);
        if(user == null) {
            ShowMessage.error("Wrong nickname or password");
            setWatermarksToLoginFrame();
        }
        else {
            login(user);
            enableBossModeFunctionsIfLoggedInUSerISBoss();
            setWatermarksToLoginFrame();
        }
    }

    private String getInputNickname() {
        return loginFrame.getNicknameTextField().getText();
    }

    private String getInputPassword() {
        return String.valueOf(loginFrame.getPasswordField().getPassword());
    }

    private void login(User user) {
        MainFrame.setLoggedInUser(user);
        TableRefresher.refreshOwnTaskTable();
        loginFrame.setVisible(false);
        mainFrame.setVisible(true);
    }

    private void enableBossModeFunctionsIfLoggedInUSerISBoss() {
        if(MainFrame.getLoggedInUser().isBoss())
            BossTaskMenageFrame.setSendTaskItemEnabled(true);
        else
            BossTaskMenageFrame.setSendTaskItemEnabled(false);
    }

    private void setWatermarksToLoginFrame() {
        loginFrame.getNicknameTextField().setText("Nickname");
        loginFrame.getNicknameTextField().setForeground(Settings.WATERMARK_TEXT_COLOR);
        loginFrame.getPasswordField().setEchoChar((char)0);
        loginFrame.getPasswordField().setText("Password");
        loginFrame.getPasswordField().setForeground(Settings.WATERMARK_TEXT_COLOR);
    }
}
