package components.custom;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarBorderProperties;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

@Component
public class DatePicker extends CalendarPanel {

    private JPopupMenu popupMenu;
    private Border borderWhenMouseOver = new LineBorder(Colors.HIGHLIGHT_COLOR);
    private Border lightGreyBorder = new LineBorder(Colors.LIGHT_GRAY);

    public DatePicker() {
        CalendarButtonsAndLabelsPainterExceptDateLabels exceptDateLabelsPainter = new CalendarButtonsAndLabelsPainterExceptDateLabels();
        DateLabelsPainter dateLabelsPainter = new DateLabelsPainter();
        PopupMenuShowerOnSelectedDate popupMenuShower = new PopupMenuShowerOnSelectedDate();
        this.popupMenu = new JPopupMenu();
        DatePickerSettings settings = new DatePickerSettings();
        settings.setVisiblePreviousYearButton(false);
        settings.setVisiblePreviousMonthButton(false);
        settings.setVisibleNextYearButton(false);
        settings.setVisibleNextMonthButton(false);
        setBorder(new LineBorder(Colors.SOFT_BLUE));
        ArrayList<CalendarBorderProperties> borderProperties = new ArrayList<>();
        borderProperties.add(new CalendarBorderProperties(
                new Point(1, 1),
                new Point(5, 5),
                Colors.SOFT_BLUE, 1));
        settings.setBorderPropertiesList(borderProperties);
        settings.setColor(DatePickerSettings.DateArea.CalendarBackgroundSelectedDate, Colors.HIGHLIGHT_COLOR);
        settings.setColor(DatePickerSettings.DateArea.CalendarBorderSelectedDate, Colors.HIGHLIGHT_COLOR);
        settings.setColorBackgroundWeekdayLabels(Colors.DARK_GRAY, false);
        settings.setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, Colors.LIGHT_GRAY);
        settings.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, Colors.LIGHT_GRAY);
        settings.setColor(DatePickerSettings.DateArea.BackgroundClearLabel, Colors.LIGHT_GRAY);
        settings.setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, Colors.LIGHT_GRAY);
        settings.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, Colors.LIGHT_GRAY);
        settings.setColor(DatePickerSettings.DateArea.CalendarTextNormalDates, Colors.TEXT_COLOR);
        settings.setColor(DatePickerSettings.DateArea.BackgroundTopLeftLabelAboveWeekNumbers, Colors.DARK_GRAY);
        settings.setColor(DatePickerSettings.DateArea.CalendarTextWeekdays, Colors.TEXT_COLOR);
        settings.setLocale(Locale.ENGLISH);
        settings.setFormatForTodayButton(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        drawCalendar();
        JLabel monthLabel = getMonthLabel();
        monthLabel.addMouseListener(exceptDateLabelsPainter);
        JLabel yearLabel = getYearLabel();
        yearLabel.addMouseListener(exceptDateLabelsPainter);
        JLabel todayLabel = getTodayLabel();
        todayLabel.addMouseListener(exceptDateLabelsPainter);
        JLabel clearLabel = getClearLabel();
        clearLabel.addMouseListener(exceptDateLabelsPainter);
        for(JLabel label : getDateLabels()) {
            label.addMouseListener(dateLabelsPainter);
            label.addMouseListener(popupMenuShower);
        }
        setSettings(settings);
    }

    private JLabel getMonthLabel() {
        JPanel upperPanel = (JPanel) getComponent(0);
        JPanel middlePanel = (JPanel) upperPanel.getComponent(2);
        JPanel panelWithMonthAndYearLabels = (JPanel) middlePanel.getComponent(0);
        return (JLabel) panelWithMonthAndYearLabels.getComponent(0);
    }

    private JLabel getYearLabel() {
        JPanel upperPanel = (JPanel) getComponent(0);
        JPanel middlePanel = (JPanel) upperPanel.getComponent(2);
        JPanel panelWithMonthAndYearLabels = (JPanel) middlePanel.getComponent(0);
        return (JLabel) panelWithMonthAndYearLabels.getComponent(1);
    }

    private JLabel getTodayLabel() {
        JPanel bottomPanel = (JPanel) getComponent(2);
        return (JLabel) bottomPanel.getComponent(0);
    }

    private JLabel getClearLabel() {
        JPanel bottomPanel = (JPanel) getComponent(2);
        return (JLabel) bottomPanel.getComponent(1);
    }

    private ArrayList<JLabel> getDateLabels() {
        ArrayList<JLabel> listOfDays = new ArrayList<>();
        JPanel middlePanel = (JPanel) getComponent(1);
        for(int i = 0; i<42; i++) {
            JLabel label = (JLabel) middlePanel.getComponent(i);
            listOfDays.add(label);
        }
        return listOfDays;
    }

    public void addMenuItem(JMenuItem menuItem) {
        popupMenu.add(menuItem);
    }

    class CalendarButtonsAndLabelsPainterExceptDateLabels extends MouseAdapter {

        private EmptyBorder labelIndicatorEmptyBorder = new EmptyBorder(3, 2, 3, 2);
        private Border highlightBorder = new CompoundBorder(new LineBorder(Colors.HIGHLIGHT_COLOR), labelIndicatorEmptyBorder);
        private Border invisibleBorder = new CompoundBorder(new LineBorder(Colors.LIGHT_GRAY), labelIndicatorEmptyBorder);

        @Override
        public void mouseEntered(MouseEvent e) {
            JComponent component = (JComponent) e.getComponent();
            component.setBackground(Colors.LIGHT_GRAY);
            component.setBorder(highlightBorder);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JComponent component = (JComponent) e.getComponent();
            component.setBackground(Colors.LIGHT_GRAY);
            component.setBorder(invisibleBorder);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            e.getComponent().setBackground(Colors.HIGHLIGHT_COLOR);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            e.getComponent().setBackground(Colors.LIGHT_GRAY);
        }
    }

    class DateLabelsPainter extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            if(isActiveLabel(label))
                label.setBorder(borderWhenMouseOver);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            label.setBorder(lightGreyBorder);
        }

        private boolean isActiveLabel(JLabel label) {
            try{
                Integer.parseInt(label.getText());
                return true;
            } catch (ClassCastException | NumberFormatException e){
                return false;
            }
        }
    }

    class PopupMenuShowerOnSelectedDate extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            checkPopup(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            checkPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            checkPopup(e);
        }

        private void checkPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
}
