package task.managment;

import login.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import registration.RegistrationFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component("taskManagementFramesCloseActions")
public class FramesCloser {

    private WorkFrame workFrame;
    private LoginFrame loginFrame;
    private RegistrationFrame registrationFrame;
    private TaskUpdater taskUpdater;
    private TaskAdder taskAdder;

    @Autowired
    public FramesCloser(WorkFrame workFrame,
                        LoginFrame loginFrame,
                        RegistrationFrame registrationFrame,
                        TaskUpdater taskUpdater,
                        TaskAdder taskAdder) {
        this.workFrame = workFrame;
        this.loginFrame = loginFrame;
        this.registrationFrame = registrationFrame;
        this.taskUpdater = taskUpdater;
        this.taskAdder = taskAdder;
        setFramesCloseActions();
    }

    private void setFramesCloseActions() {
        loginFrame.setCloseAction(loginFrameCloseAction());
        registrationFrame.setCloseAction(registrationFrameCloseAction());
        workFrame.setCloseAction(workFrameCloseAction());
        taskUpdater.getTaskUpdateFrame().setCloseAction(updateFrameCloseAction());
        taskAdder.getTaskAddFrame().setCloseAction(addTaskFrameCloseAction());
    }

    private MouseAdapter loginFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        };
    }

    private MouseAdapter registrationFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registrationFrame.dispose();
                loginFrame.showFrame();
            }
        };
    }

    private MouseAdapter workFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WorkFrame.setLoggedInUser(null);
                workFrame.dispose();
                loginFrame.showFrame();
            }
        };
    }

    private MouseAdapter updateFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taskUpdater.getTaskUpdateFrame().dispose();
                workFrame.setEnabled(true);
                workFrame.toFront();
            }
        };
    }

    private MouseAdapter addTaskFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taskAdder.getTaskAddFrame().dispose();
                workFrame.setEnabled(true);
                workFrame.toFront();
            }
        };
    }
}
