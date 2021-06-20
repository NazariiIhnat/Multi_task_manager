package components.custom.watermark;

import components.Colors;

import javax.swing.*;
import java.awt.event.FocusEvent;

public class PasswordWatermarker extends Watermarker {

    PasswordWatermarker(String watermarkText) {
        super(watermarkText);
    }

    @Override
    public void focusGained(FocusEvent e) {
        JPasswordField passwordField = (JPasswordField) e.getComponent();
        if (String.valueOf(passwordField.getPassword()).equals(getWatermarkText())
                & passwordField.getForeground().equals(Colors.WATERMARK_TEXT_COLOR)) {
            passwordField.setEchoChar('*');
            passwordField.setText("");
            passwordField.setForeground(Colors.TEXT_COLOR);
        }
    }

    @Override
    public void focusLost (FocusEvent e){
        JPasswordField passwordField = (JPasswordField) e.getComponent();
        if (isEmptyPassword(passwordField) & passwordField.getForeground().equals(Colors.TEXT_COLOR)) {
            passwordField.setEchoChar((char)0);
            passwordField.setText(getWatermarkText());
            passwordField.setForeground(Colors.WATERMARK_TEXT_COLOR);
        }
    }
    private boolean isEmptyPassword (JPasswordField passwordField){
        return String.valueOf(passwordField.getPassword()).isEmpty();
    }
}

