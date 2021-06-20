package login;

import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ShowMessage;
import utils.TableRefresher;
import work.boss_mode.BossTaskMenageFrame;
import work.main.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class Login implements ActionListener {

    private LoginFrame loginFrame;
    private MainFrame mainFrame;

    @Autowired
    public Login(LoginFrame loginFrame, MainFrame mainFrame) {
        this.loginFrame = loginFrame;
        this.mainFrame = mainFrame;
        loginFrame.getLoginButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname = getInputNickname();
        String password = getInputPassword();
        User user = UserDAO.getLoggedInUser(nickname, password);
        if(user == null)
            ShowMessage.error("Wrong nickname or password");
        else {
            login(user);
            enableBossModeFunctionsIfLoggedInUSerISBoss();
        }
        loginFrame.clearTextComponentsAndSetWatermarks();
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
}
