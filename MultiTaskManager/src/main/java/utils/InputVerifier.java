package utils;

import javax.swing.text.JTextComponent;

public class InputVerifier {
    public static boolean textComponentIsEmpty (JTextComponent component, String watermarkText) {
        return component.getText().split(" ").length == 0
                || component.getText().equals(watermarkText)
                & component.getForeground().equals(Settings.WATERMARK_TEXT_COLOR);
    }
}
