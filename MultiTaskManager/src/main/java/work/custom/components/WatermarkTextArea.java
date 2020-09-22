package work.custom.components;

import utils.Settings;

import javax.swing.*;

public class WatermarkTextArea extends JTextArea {

    private final String watermarkText;

    public WatermarkTextArea(String watermarkText) {
        this.watermarkText = watermarkText;
        addFocusListener(new Watermarker(watermarkText));
    }

    public void setWatermark() {
        setText(watermarkText);
        setForeground(Settings.WATERMARK_TEXT_COLOR);
    }

    public boolean isEmpty() {
        return getText().split(" ").length == 0 || getText().equals(watermarkText);
    }

    public String getWatermarkText() {
        return watermarkText;
    }
}
