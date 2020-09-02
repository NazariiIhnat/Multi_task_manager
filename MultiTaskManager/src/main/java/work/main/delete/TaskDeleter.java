package work.main.delete;

import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.TableRefresher;
import work.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.VetoableChangeListener;
import java.util.List;

@Component("employeeTaskDeleter")
public class TaskDeleter {

    private MainFrame mainFrame;

    @Autowired
    public TaskDeleter (MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initDeleteTaskMenuItem();
    }

    private void initDeleteTaskMenuItem() {
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(deleteAction());
        mainFrame.getOwnTaskTable().addPopupMenuItem(deleteMenuItem);
    }

    private ActionListener deleteAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task taskToDelete = mainFrame.getOwnTaskTable().getSelectedTask();
                TaskDAO.delete(taskToDelete);
                TableRefresher.refreshOwnTaskTable();
            }
        };
    }
}
