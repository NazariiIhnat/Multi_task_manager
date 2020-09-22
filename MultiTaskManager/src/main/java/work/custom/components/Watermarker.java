package work.custom.components;

import utils.Settings;

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
                & textComponent.getForeground().equals(Settings.WATERMARK_TEXT_COLOR)){
            textComponent.setText("");
            textComponent.setForeground(Settings.TEXT_COLOR);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextComponent textComponent = (JTextComponent) e.getComponent();
        if(isEmpty(textComponent.getText()) & textComponent.getForeground().equals(Settings.TEXT_COLOR)){
            textComponent.setText(watermarkText);
            textComponent.setForeground(Settings.WATERMARK_TEXT_COLOR);
        }
    }

    boolean isEmpty(String text) {
        return text.equals("") || text.split(" ").length == 0;
    }

    public String getWatermarkText() {
        return watermarkText;
    }
}
