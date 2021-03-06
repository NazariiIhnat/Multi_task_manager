package components.custom.watermark;

import components.custom.Colors;
import javax.swing.*;

public class WatermarkTextArea extends JTextArea {

    private final String watermarkText;

    public WatermarkTextArea(String watermarkText) {
        this.watermarkText = watermarkText;
        addFocusListener(new Watermarker(watermarkText));
        setText(watermarkText);
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }

    public boolean isEmpty() {
        return getText().split(" ").length == 0 |
                getText().equals("") |
                getText().equals(watermarkText) && getForeground().equals(Colors.WATERMARK_TEXT_COLOR);
    }

    public void clearInputAndSetWatermark() {
        setText(watermarkText);
        setForeground(Colors.WATERMARK_TEXT_COLOR);
    }
}
