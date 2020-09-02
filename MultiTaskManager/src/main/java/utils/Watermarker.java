package utils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Watermarker {

    public static void addWatermarkToTextComponentWhenLostFocus(JTextComponent textComponent, String watermarkText){
        textComponent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textComponent.getText().equals(watermarkText)
                        & textComponent.getForeground().equals(Settings.WATERMARK_TEXT_COLOR)){
                    textComponent.setText("");
                    textComponent.setForeground(Settings.TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(isEmpty(textComponent.getText()) & textComponent.getForeground().equals(Settings.TEXT_COLOR)){
                    textComponent.setText(watermarkText);
                    textComponent.setForeground(Settings.WATERMARK_TEXT_COLOR);
                }
            }
        });
    }

    public static void addWatermarkToPasswordFieldWhenLostFocus(JPasswordField passwordField, String watermarkText) {
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(String.valueOf(passwordField.getPassword()).equals(watermarkText)
                        & passwordField.getForeground().equals(Settings.WATERMARK_TEXT_COLOR)){
                    passwordField.setEchoChar('*');
                    passwordField.setText("");
                    passwordField.setForeground(Settings.TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (isEmptyPassword(passwordField) & passwordField.getForeground().equals(Settings.TEXT_COLOR)) {
                    passwordField.setEchoChar((char)0);
                    passwordField.setText(watermarkText);
                    passwordField.setForeground(Settings.WATERMARK_TEXT_COLOR);
                }
            }
        });
    }

    private static boolean isEmpty(String text) {
        return text.equals("") || text.split(" ").length == 0;
    }

    private static boolean isEmptyPassword(JPasswordField passwordField) {
        return isEmpty(String.valueOf(passwordField.getPassword()));
    }



}
