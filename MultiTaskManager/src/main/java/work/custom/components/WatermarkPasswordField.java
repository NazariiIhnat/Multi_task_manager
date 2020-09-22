package work.custom.components;

import utils.Settings;

import javax.swing.*;

public class WatermarkPasswordField extends JPasswordField {

    private final String watermarkText;

    public WatermarkPasswordField(String watermarkText) {
        this.watermarkText = watermarkText;
        addFocusListener(new PasswordWatermarker(watermarkText));
    }

    public void setWatermark() {
        setEchoChar((char) 0);
        setText(watermarkText);
        setForeground(Settings.WATERMARK_TEXT_COLOR);

    }

    public String getWatermarkText() {
        return watermarkText;
    }
}
