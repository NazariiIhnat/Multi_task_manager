package utils;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public final class Settings {

    public static final Color LIGHT_GRAY = new Color(69, 73, 74);
    public static final Color DARK_GRAY = new Color(60, 63, 65);
    public static final Color SOFT_BLUE = new Color(70, 109, 148);
    public static final Color TEXT_COLOR = new Color(187, 187, 187);
    public static final Color HIGHLIGHT_COLOR = new Color(75, 110, 175);
    public static final Color WATERMARK_TEXT_COLOR = new Color(120, 120, 120);

    static {

        /*UIManager.put("Menu.selectionBackground", HIGHLIGHT_COLOR);
        UIManager.put("Menu.borderPainted",false);

        UIManager.put("MenuItem.selectionForeground", Color.BLACK);
        UIManager.put("MenuItem.selectionBackground", HIGHLIGHT_COLOR);
        UIManager.put("MenuItem.background", LIGHT_GREY);
        UIManager.put("MenuItem.selectionBorder", HIGHLIGHT_BORDER);
        UIManager.put("MenuItem.foreground", Color.BLACK);
        UIManager.put("MenuItem.border", LIGHT_GREY_BORDER);

        UIManager.put("PopupMenu.background", LIGHT_GREY);
        UIManager.put("PopupMenu.border", new LineBorder(Color.BLACK));

        UIManager.put("CheckBox.background", LIGHT_GREY);

        UIManager.put("Table.focusCellHighlightBorder", Settings.LIGHT_GREY_BORDER);

        UIManager.put("ToolTip.background", HIGHLIGHT_COLOR);
        UIManager.put("ToolTip.border", LIGHT_GREY_BORDER);*/
    }
}
