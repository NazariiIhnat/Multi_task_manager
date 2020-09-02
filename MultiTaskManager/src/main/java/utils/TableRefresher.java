package utils;

import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import dao.TaskDAO;
import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.boss_mode.BossTaskMenageFrame;
import work.main.MainFrame;
import work.custom.components.DatePicker;
import work.custom.components.AbstractTaskTable;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

@Component
public class TableRefresher {

    private static DatePicker datePicker;
    private static MainFrame mainFrame;
    private static BossTaskMenageFrame bossTaskMenageFrame;
    private static List<Task> shownInOwnTaskTableTasks = new ArrayList<>();
    private static List<Task> shownInSelectedUserTaskTableTasks = new ArrayList<>();

    @Autowired
    public TableRefresher(DatePicker datePicker, MainFrame mainFrame, BossTaskMenageFrame bossTaskMenageFrame) {
        TableRefresher.datePicker = datePicker;
        TableRefresher.mainFrame = mainFrame;
        TableRefresher.bossTaskMenageFrame = bossTaskMenageFrame;
        datePicker.addCalendarListener(changeSelectedDaysAction());
    }

    private CalendarListener changeSelectedDaysAction() {
        return new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent event) {
                refreshOwnTaskTable();
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent event) {
                refreshOwnTaskTable();
            }
        };
    }

    public static void refreshOwnTaskTable() {
        DefaultTableModel tableModel = (DefaultTableModel) mainFrame.getOwnTaskTable().getModel();
        shownInOwnTaskTableTasks = getTasksOfSelectedDay();
        nullifyTasksTable(tableModel);
        addTasksToTable(shownInOwnTaskTableTasks, tableModel);
    }

    private static List<Task> getTasksOfSelectedDay() {
        String nickname = MainFrame.getLoggedInUser().getNickname();
        return TaskDAO.getTasksOfUserByDate(nickname, datePicker.getSelectedDate());
    }

    private static void nullifyTasksTable(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        for(int i = rowCount - 1; i >= 0; i--)
            tableModel.removeRow(i);
    }

    private static void addTasksToTable(List<Task> tasks, DefaultTableModel tableModel) {
        int counter = 1;
        for(Task task : tasks){
            tableModel.addRow(new Object[]{
                    counter,
                    task.getId(),
                    task.getTitle(),
                    task.getSenderNickname(),
                    task.isDone(),
                    task.getDescription()});
            counter += 1;
        }
    }

    public static void refreshSelectedUsersTaskTable(User receiver){
        DefaultTableModel tableModel = (DefaultTableModel) bossTaskMenageFrame.getBossModeTaskTable().getModel();
        nullifyTasksTable(tableModel);
        shownInSelectedUserTaskTableTasks = getSelectedUserSentTasks(receiver);
        int counter = 1;
        for(Task task : shownInSelectedUserTaskTableTasks){
            tableModel.addRow(new Object[]{counter, task.getId(), task.getTitle(), task.isDone(), task.getDescription()});
            counter += 1;
        }
    }

    private static List<Task> getSelectedUserSentTasks(User user) {
        String sender = MainFrame.getLoggedInUser().getNickname();
        String receiver = user.getNickname();
        return TaskDAO.getUsersTasksBySenderNickname(receiver, sender);
    }

    public static void nullifyTaskTable(AbstractTaskTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        for(int i = rowCount - 1; i >= 0; i--)
            model.removeRow(i);
    }

    public static List<Task> getShownInOwnTaskTableTasks() {
        return shownInOwnTaskTableTasks;
    }

    public static List<Task> getShownInSelectedUserTaskTableTasks() {
        return shownInSelectedUserTaskTableTasks;
    }
}
