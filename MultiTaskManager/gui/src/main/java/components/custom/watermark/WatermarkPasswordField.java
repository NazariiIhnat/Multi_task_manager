package components.custom.watermark;

import components.Colors;

import javax.swing.*;

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
        setText("Password");
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }

    public String getWatermarkText() {
        return watermarkText;
    }
}
