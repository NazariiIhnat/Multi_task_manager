package components.custom.watermark;

import components.custom.Colors;

import javax.swing.text.JTextComponent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Watermarker implements FocusListener {

    private final String watermarkText;

    Watermarker(String watermarkText){
        this.watermarkText = watermarkText;
    }

    @Override
    public void focusGained(FocusEvent e) {
        JTextComponent textComponent = (JTextComponent) e.getComponent();
        if(textComponent.getText().equals(watermarkText)
                & textComponent.getForeground().equals(Colors.WATERMARK_TEXT_COLOR)){
            textComponent.setText("");
            textComponent.setForeground(Colors.TEXT_COLOR);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextComponent textComponent = (JTextComponent) e.getComponent();
        if(isEmpty(textComponent.getText()) & textComponent.getForeground().equals(Colors.TEXT_COLOR)){
            textComponent.setText(watermarkText);
            textComponent.setForeground(Colors.WATERMARK_TEXT_COLOR);
        }
    }

    private boolean isEmpty(String text) {
        return text.split(" ").length == 0 | text.equals("");
    }

    String getWatermarkText() {
        return watermarkText;
    }
}
