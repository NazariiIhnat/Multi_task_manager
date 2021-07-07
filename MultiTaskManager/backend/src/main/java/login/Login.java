package login;

import dao.UserDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.TableRefresher;
import message.ShowMessage;
import task.managment.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class Login implements ActionListener {

    private LoginFrame loginFrame;
    private WorkFrame workFrame;
    private TableRefresher tableRefresher;

    @Autowired
    public Login(LoginFrame loginFrame, WorkFrame workFrame, TableRefresher tableRefresher) {
        this.loginFrame = loginFrame;
        this.workFrame = workFrame;
        this.tableRefresher = tableRefresher;
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
        WorkFrame.setLoggedInUser(user);
        tableRefresher.refreshTable();
        loginFrame.setVisible(false);
        workFrame.setVisible(true);
    }

    private void enableBossModeFunctionsIfLoggedInUSerISBoss() {
        if(WorkFrame.getLoggedInUser().isBoss())
            workFrame.showBossModeFunctions();
        else
            workFrame.hideBossModeFunctions();
    }
}
