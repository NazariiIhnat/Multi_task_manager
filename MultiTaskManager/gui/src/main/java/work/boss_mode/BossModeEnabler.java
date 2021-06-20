package work.boss_mode;

import components.custom.CustomFrame;
import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ShowMessage;
import work.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

@Component
public class BossModeEnabler extends CustomFrame {

    private static final String BOSS_MODE_PASSWORD = "1";
    private static final String DISABLE_BOSS_MODE = "2";
    private JPasswordField passwordField;
    private MainFrame mainFrame;
    private BossTaskMenageFrame bossTaskMenageFrame;

    @Autowired
    public BossModeEnabler(MainFrame mainFrame, BossTaskMenageFrame bossTaskMenageFrame) {
        super("Boss mode", 100, 100, 194, 72);
        this.mainFrame = mainFrame;
        this.bossTaskMenageFrame = bossTaskMenageFrame;
        initComponents();
        initPopupMenuItem();
    }

    private void initComponents() {
        passwordField = new JPasswordField();
        passwordField.setBounds(10, 37, 114, 22);
        add(passwordField);

        JButton okButton = new JButton("OK");
        okButton.setBounds(136, 37, 48, 22);
        add(okButton);
        okButton.addActionListener(enableBossModeAction());

        setLocationRelativeTo(getOwner());
    }

    private void initPopupMenuItem() {
        JMenuItem menuItem = new JMenuItem("Boss mode");
        menuItem.addActionListener(showFrame());
        mainFrame.addItemToProfileMenu(menuItem);
    }

    private ActionListener showFrame() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setEnabled(false);
                setVisible(true);
                toFront();
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
        return Arrays.equals(passwordField.getPassword(), BOSS_MODE_PASSWORD.toCharArray());
    }

    private boolean isCorrectDisableBossModePassword() {
        return (Arrays.equals(passwordField.getPassword(), DISABLE_BOSS_MODE.toCharArray()));
    }

    private void enableBossMode() {
        MainFrame.getLoggedInUser().setBoss(true);
        UserDAO.saveOrUpdate(MainFrame.getLoggedInUser());
        ShowMessage.success("Welcome to boss mode");
        enableBossModeFunctions();
        closeFrame();
    }

    private void disableBossMode() {
        MainFrame.getLoggedInUser().setBoss(false);
        UserDAO.saveOrUpdate(MainFrame.getLoggedInUser());
        ShowMessage.success("Boss mode disabled");
        disableBossModeFunctions();
        closeFrame();
    }

    private void enableBossModeFunctions() {
        bossTaskMenageFrame.getSendTaskItem().setEnabled(true);
    }

    private void disableBossModeFunctions() {
        bossTaskMenageFrame.getSendTaskItem().setEnabled(false);
    }

    private void closeFrame() {
        setVisible(false);
        passwordField.setText(null);
        mainFrame.setEnabled(true);
        mainFrame.toFront();
    }

    @Override
    public void closeAction() {
        dispose();
        mainFrame.setEnabled(true);
        mainFrame.toFront();
    }
}
