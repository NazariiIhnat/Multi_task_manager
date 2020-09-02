package work.custom.components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablePopupMenuShower extends MouseAdapter {

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
            JPopupMenu jPopupMenu = (JPopupMenu) e.getSource();
            jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
