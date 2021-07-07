package components.custom.watermark;

import components.custom.Colors;

import javax.swing.*;
import java.util.Arrays;

public class WatermarkPasswordField extends JPasswordField {

    private final String watermarkText;

    public WatermarkPasswordField(String watermarkText) {
        this.watermarkText = watermarkText;
        addFocusListener(new PasswordWatermarker(watermarkText));
        setEchoChar((char) 0);
        setText(watermarkText);
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }

    public void clearInputAndSetWatermark() {
        setEchoChar((char)0);
        setText(watermarkText);
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }

    public boolean isEmpty() {
        return getPassword().length == 0 |
                Arrays.toString(getPassword()).equals(watermarkText)
                && getForeground().equals(Colors.WATERMARK_TEXT_COLOR);
    }
}
