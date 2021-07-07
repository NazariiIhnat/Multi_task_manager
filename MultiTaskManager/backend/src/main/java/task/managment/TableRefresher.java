package task.managment;

import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import dao.TaskDAO;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

@Component ("workFrameTableRefresher")
public class TableRefresher {

    private WorkFrame workFrame;
    private List<Task> shownInTableTasks;

    @Autowired
    public TableRefresher(WorkFrame workFrame) {
        this.workFrame = workFrame;
        shownInTableTasks = new ArrayList<>();
        workFrame.getDatePicker().addCalendarListener(refreshTableWhenDateChanged());
    }

    private CalendarListener refreshTableWhenDateChanged() {
        return new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent calendarSelectionEvent) {
                refreshTable();
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent yearMonthChangeEvent) {
                refreshTable();
            }
        };
    }

    public void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) workFrame.getTaskManageTable().getModel();
        shownInTableTasks = getTasksOfSelectedDay();
        nullifyTasksTable(tableModel);
        addTasksToTable(shownInTableTasks, tableModel);
    }

    private List<Task> getTasksOfSelectedDay() {
        String nickname = WorkFrame.getLoggedInUser().getNickname();
        return TaskDAO.getTasksOfUserByDate(nickname, workFrame.getDatePicker().getSelectedDate());
    }

    private void nullifyTasksTable(DefaultTableModel tableModel) {
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

    public List<Task> getShownInTableTasks() {
        return shownInTableTasks;
    }
}
