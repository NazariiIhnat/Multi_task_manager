package task.managment.bossmode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.managment.WorkFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component("bossModeFramesCloser")
public class FramesCloser {
    private WorkFrame workFrame;
    private BossModeFrame bossModeFrame;
    private BossModeEnableFrame bossModeEnableFrame;
    private TaskSendFrame taskSendFrame;
    private TaskUpdater taskUpdater;

    @Autowired
    public FramesCloser(WorkFrame workFrame,
                        BossModeFrame bossModeFrame,
                        BossModeEnableFrame bossModeEnableFrame,
                        TaskSendFrame taskSendFrame,
                        TaskUpdater taskUpdater) {
        this.workFrame = workFrame;
        this.bossModeFrame = bossModeFrame;
        this.bossModeEnableFrame = bossModeEnableFrame;
        this.taskSendFrame = taskSendFrame;
        this.taskUpdater = taskUpdater;
        setFramesCloseActions();
    }
    private void setFramesCloseActions() {
        bossModeEnableFrame.setCloseAction(bossModeEnableFrameCloseAction());
        bossModeFrame.setCloseAction(bossModeFrameCloseAction());
        taskSendFrame.setCloseAction(taskSendFrameCloseAction());
        taskUpdater.getTaskUpdateFrame().setCloseAction(bossModeTaskUpdateFrameCloseAction());
    }

    private MouseAdapter bossModeEnableFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bossModeEnableFrame.dispose();
                workFrame.setEnabled(true);
                workFrame.toFront();
            }
        };
    }

    private MouseAdapter bossModeFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bossModeFrame.dispose();
                workFrame.toFront();
            }
        };
    }

    private MouseAdapter taskSendFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taskSendFrame.dispose();
                bossModeFrame.setEnabled(true);
                bossModeFrame.toFront();
            }
        };
    }

    private MouseAdapter bossModeTaskUpdateFrameCloseAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taskUpdater.getTaskUpdateFrame().dispose();
                bossModeFrame.setEnabled(true);
                bossModeFrame.toFront();
            }
        };
    }
}
