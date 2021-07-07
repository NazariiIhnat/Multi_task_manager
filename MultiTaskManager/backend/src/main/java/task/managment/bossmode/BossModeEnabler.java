package task.managment.bossmode;

import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.WorkFrame;
import message.ShowMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

@Component
public class BossModeEnabler {

    private static final String BOSS_MODE_PASSWORD = "1";
    private static final String DISABLE_BOSS_MODE_PASSWORD = "2";
    private WorkFrame workFrame;
    private BossModeEnableFrame bossModeEnableFrame;

    @Autowired
    public BossModeEnabler(WorkFrame workFrame, BossModeEnableFrame bossModeEnableFrame) {
        this.workFrame = workFrame;
        this.bossModeEnableFrame = bossModeEnableFrame;
        workFrame.getMenu().getBossModeMenuItem().addActionListener(showEnableBossModeFrameAction());
        bossModeEnableFrame.getOkButton().addActionListener(enableBossModeAction());
    }

    private ActionListener showEnableBossModeFrameAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workFrame.setEnabled(false);
                bossModeEnableFrame.showFrame();
            }
        };
    }

    private ActionListener enableBossModeAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isCorrectBossModePassword()) {
                    enableBossMode();
                } else if(isCorrectDisableBossModePassword()){
                    disableBossMode();
                }
                else
                    ShowMessage.error("Wrong boss mode password");
            }
        };
    }

    private boolean isCorrectBossModePassword() {
        return Arrays.equals(bossModeEnableFrame.getPasswordField().getPassword(), BOSS_MODE_PASSWORD.toCharArray());
    }

    private boolean isCorrectDisableBossModePassword() {
        return (Arrays.equals(bossModeEnableFrame.getPasswordField().getPassword(),
                DISABLE_BOSS_MODE_PASSWORD.toCharArray()));
    }

    private void enableBossMode() {
        WorkFrame.getLoggedInUser().setBoss(true);
        UserDAO.saveOrUpdate(WorkFrame.getLoggedInUser());
        ShowMessage.success("Welcome to boss mode");
        workFrame.showBossModeFunctions();
        closeFrame();
    }

    private void disableBossMode() {
        WorkFrame.getLoggedInUser().setBoss(false);
        UserDAO.saveOrUpdate(WorkFrame.getLoggedInUser());
        ShowMessage.success("Boss mode disabled");
        workFrame.hideBossModeFunctions();
        closeFrame();
    }

    private void closeFrame() {
        bossModeEnableFrame.setVisible(false);
        workFrame.setEnabled(true);
        workFrame.toFront();
    }
}
