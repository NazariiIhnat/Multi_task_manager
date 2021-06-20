package components.custom.watermark;

import components.Colors;

import javax.swing.*;

public class WatermarkTextField extends JTextField {

    private final String watermarkText;

    public WatermarkTextField(String watermarkText) {
        this.watermarkText = watermarkText;
        addFocusListener(new Watermarker(watermarkText));
        setText(watermarkText);
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }

    public boolean isEmpty() {
        return getText().split(" ").length == 0 || getText().equals(watermarkText);
    }

    public void clearInputAndSetWatermark() {
        setText(watermarkText);
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }

    public String getWatermarkText() {
        return watermarkText;
    }
}
