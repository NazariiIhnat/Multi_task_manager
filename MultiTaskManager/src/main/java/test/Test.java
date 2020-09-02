package test;

import work.custom.components.TablePopupMenuShower;
import work.main.OwnTaskTable;

import javax.swing.*;

public class Test extends JFrame {
    public Test() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(100, 100, 300, 300);
        setVisible(true);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 150, 150);
        OwnTaskTable taskTable = new OwnTaskTable();
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.addMouseListener(new TablePopupMenuShower());
        taskTable.add(popupMenu);
        scrollPane.add(taskTable);

        add(scrollPane);
    }

    public static void main(String[] args) {
        Test test = new Test();
    }
}
