package task.managment;

import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import components.custom.Colors;
import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@Component
public class DescriptionTextAreaRefresher {

    private WorkFrame workFrame;

    @Autowired
    public DescriptionTextAreaRefresher(WorkFrame workFrame) {
        this.workFrame = workFrame;
        workFrame.getTaskManageTable()
                .getSelectionModel()
                .addListSelectionListener(refreshDescriptionTextAreaWhenTaskSelected());
        workFrame.getDatePicker().addCalendarListener(nullifyDescriptionTextAreaWhenDateChanged());
    }

    private ListSelectionListener refreshDescriptionTextAreaWhenTaskSelected() {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Task selectedTask = workFrame.getTaskManageTable().getSelectedTask();
                if(selectedTask != null) {
                    workFrame.getDescriptionTextArea().setForeground(Colors.TEXT_COLOR);
                    workFrame.getDescriptionTextArea().setText(selectedTask.getDescription());
                }
            }
        };
    }

    private CalendarListener nullifyDescriptionTextAreaWhenDateChanged() {
        return new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent calendarSelectionEvent) {
                workFrame.getDescriptionTextArea().clearInputAndSetWatermark();
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent yearMonthChangeEvent) {
                workFrame.getDescriptionTextArea().clearInputAndSetWatermark();
            }
        };
    }
}
