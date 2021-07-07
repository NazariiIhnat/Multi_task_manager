package logout;

import login.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.WorkFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class Logout implements ActionListener {

    private WorkFrame workFrame;
    private LoginFrame loginFrame;

    @Autowired
    public Logout(WorkFrame workFrame, LoginFrame loginFrame) {
        this.workFrame = workFrame;
        this.loginFrame = loginFrame;
        workFrame.getMenu().getLogoutMenuItem().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Frame frame : Frame.getFrames())
            frame.dispose();
        workFrame.dispose();
        WorkFrame.setLoggedInUser(null);
        loginFrame.showFrame();
    }
}
