package logout;

import login.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@Component
public class Logout {

    private MainFrame mainFrame;
    private LoginFrame loginFrame;

    @Autowired
    public Logout(MainFrame mainFrame, LoginFrame loginFrame) {
        this.mainFrame = mainFrame;
        this.loginFrame = loginFrame;
        initLogoutMenuItem();
    }

    private void initLogoutMenuItem() {
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        mainFrame.addItemToProfileMenu(logoutItem);
    }
    private void logout() {
        for(Frame frame : Frame.getFrames())
            frame.dispose();
        mainFrame.dispose();
        MainFrame.setLoggedInUser(null);
        loginFrame.showFrame();
    }
}
